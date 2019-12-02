package suite

import ca.clinia.search.model.IndexName
import ca.clinia.search.model.multipleindex.IndexQuery
import ca.clinia.search.model.places.PlaceType
import ca.clinia.search.model.places.PlacesQuery
import ca.clinia.search.model.search.Query
import ca.clinia.search.model.suggest.SuggestionQuery
import clientPlaces
import clientSearch
import io.ktor.client.features.ResponseException
import io.ktor.client.response.readText
import io.ktor.content.TextContent
import runBlocking
import shouldEqual
import kotlin.test.Test


internal class TestSuiteSearch {

    private val suffix = "search"
    private val indexName = IndexName("health_facility")

    private val search = clientSearch.initIndex(indexName)
    private val places = clientPlaces

    @Test
    fun singleIndex() {
        runBlocking {
            search.apply {
                try {
                    val responseSearch = search(Query(query = ""))

                    responseSearch.meta.perPage shouldEqual 20
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

    @Test
    fun places() {
        runBlocking {
            val response = places.searchPlaces(PlacesQuery("Lon", listOf(PlaceType.Place, PlaceType.PostCode), listOf("CA", "US"), 1))
            response.suggestions.size shouldEqual 1
        }
    }

    @Test
    fun suggest() {
        runBlocking {
            val response = clientSearch.suggest(
                SuggestionQuery("sons")
            )

            response.suggestions.size shouldEqual 5
        }
    }
}