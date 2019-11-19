package suite

import ca.clinia.search.model.IndexName
import ca.clinia.search.model.search.Query
import ca.clinia.search.transport.RequestOptions
import clientSearch
import io.ktor.client.features.ResponseException
import io.ktor.client.response.readBytes
import io.ktor.client.response.readText
import io.ktor.http.content.TextContent
import runBlocking
import shouldEqual
import kotlin.test.Test


internal class TestSuiteSearch {

    private val suffix = "search"
    private val indexName = IndexName("health_facility")

    private val search = clientSearch.initIndex(indexName)

    @Test
    fun test() {
        runBlocking {
            search.apply {
                try {
                    val responseSearch = search(Query(query = ""))

                    responseSearch.total shouldEqual 20
                } catch (exception: ResponseException) {
                    val request = exception.response.call.request
                    val body = (request.content as TextContent).text
                    println(body)

                    val content = exception.response.readText()
                    println(content)
                }



            }
        }
    }
}