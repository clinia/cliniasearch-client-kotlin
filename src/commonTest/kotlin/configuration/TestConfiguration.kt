package configuration

import BuildConfig
import ca.clinia.search.configuration.*
import ca.clinia.search.model.APIKey
import ca.clinia.search.model.ApplicationID
import ca.clinia.search.transport.searchHosts
import io.ktor.client.features.logging.LogLevel
import shouldBeNull
import shouldEqual
import kotlin.test.Test


internal class TestConfiguration {

    private val applicationID = ApplicationID("applicationID")
    private val apiKey = APIKey("apiKey")

    @Test
    fun testCliniaSearchClient() {
        CliniaSearchClient.version shouldEqual BuildConfig.version
    }

    @Test
    fun configurationSearch() {
        ConfigurationSearch(applicationID, apiKey).apply {
            writeTimeout shouldEqual defaultWriteTimeout
            readTimeout shouldEqual defaultReadTimeout
            logLevel shouldEqual LogLevel.NONE
            hosts shouldEqual applicationID.searchHosts
            defaultHeaders.shouldBeNull()
            engine.shouldBeNull()
            httpClientConfig.shouldBeNull()
        }
    }
}