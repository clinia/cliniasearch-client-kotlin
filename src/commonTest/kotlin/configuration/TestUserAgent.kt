package configuration

import BuildConfig
import ca.clinia.search.client.ClientSearch
import ca.clinia.search.configuration.CliniaSearchClient
import ca.clinia.search.configuration.ConfigurationSearch
import ca.clinia.search.configuration.clientUserAgent
import ca.clinia.search.dsl.requestOptions
import ca.clinia.search.model.APIKey
import ca.clinia.search.model.ApplicationID
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respondBadRequest
import io.ktor.client.engine.mock.respondOk
import io.ktor.client.features.ResponseException
import io.ktor.client.features.UserAgent
import io.ktor.client.request.HttpRequestBuilder
import runBlocking
import shouldBeTrue
import shouldEqual
import shouldFailWith
import kotlin.test.Test


internal class TestUserAgent {

    private val applicationID = ApplicationID("applicationID")
    private val apiKey = APIKey("apiKey")
    private val userAgentKey = "User-Agent"

    @Test
    fun testUserAgent() {
        val version = CliniaSearchClient.version

        clientUserAgent(version) shouldEqual "Clinia for Kotlin ($version)"
    }

    @Test
    fun overridingUserAgentInConfigurationShouldBeIgnored() {
        runBlocking {
            val configuration = ConfigurationSearch(
                applicationID = applicationID,
                engine = MockEngine { respondOk() },
                apiKey = apiKey,
                httpClientConfig = {
                    install(UserAgent) {
                        agent = "Test"
                    }
                })
            val client = ClientSearch(configuration)
            val request = client.httpClient.execute(HttpRequestBuilder())
            val headers = request.response.call.request.headers

            headers.contains(userAgentKey).shouldBeTrue()
            headers[userAgentKey] shouldEqual clientUserAgent(BuildConfig.version)
        }
    }

    @Test
    fun overridingUserAgentInRequestOptionsShouldNotBeIgnored() {
        runBlocking {
            val expected = "My User Agent"
            val configuration = ConfigurationSearch(
                applicationID = applicationID,
                engine = MockEngine {
                    respondBadRequest()
                },
                apiKey = apiKey
            )
            val client = ClientSearch(configuration)
            val requestOptions = requestOptions { header(userAgentKey, expected) }
            val request = shouldFailWith<ResponseException> {
                client.multipleQueries(listOf(), requestOptions)
            }
            val headers = request.response.call.request.headers

            headers[userAgentKey] shouldEqual expected
        }
    }
}