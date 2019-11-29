package suite

import ca.clinia.search.dsl.query
import ca.clinia.search.helper.and
import ca.clinia.search.model.IndexName
import ca.clinia.search.model.multipleindex.IndexQuery
import ca.clinia.search.model.search.BoundingBox
import ca.clinia.search.model.search.Query
import clientSearch
import io.ktor.client.features.ResponseException
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
    fun singleIndex() {
        runBlocking {
            search.apply {
                try {
                    val responseSearch = search(Query(query = ""))

                    responseSearch.meta.perPage shouldEqual 65
                    responseSearch.records.first()
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

    @Test
    fun multiQueries() {
        runBlocking {
            val response = clientSearch.multipleQueries(
                listOf(
                    IndexQuery(indexName = IndexName("health_facility"), query = Query(query = "")),
                    IndexQuery(indexName = IndexName("professional"), query = Query(query = ""))
                )
            )

            response.results.size shouldEqual 2
        }
    }
}