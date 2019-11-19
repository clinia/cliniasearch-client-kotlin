package client

import ca.clinia.search.client.ClientSearch
import ca.clinia.search.configuration.ConfigurationSearch
import ca.clinia.search.helper.toAPIKey
import ca.clinia.search.helper.toApplicationID
import ca.clinia.search.model.IndexName
import ca.clinia.search.model.response.ResponseSearch
import ca.clinia.search.serialize.JsonNoDefaults
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.io.ByteReadChannel
import runBlocking
import shouldEqual
import kotlin.test.Test


internal class TestMockEngine {

    private val apiKey = "apiKey".toAPIKey()
    private val appID = "appID".toApplicationID()
    private val responseSearch = ResponseSearch(recordsOrNull = listOf())
    private val engine = MockEngine {
        respond(
            status = HttpStatusCode.OK,
            headers = headersOf("Content-Type", listOf(ContentType.Application.Json.toString())),
            content = ByteReadChannel(JsonNoDefaults.stringify(ResponseSearch.serializer(), responseSearch))
        )
    }
    private val client = ClientSearch(ConfigurationSearch(appID, apiKey, engine = engine))

    @Test
    fun mock() {
        runBlocking {
            client.initIndex(IndexName("index_name")).search() shouldEqual responseSearch
        }
    }
}