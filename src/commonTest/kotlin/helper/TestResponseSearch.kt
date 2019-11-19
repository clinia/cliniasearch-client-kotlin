package helper


import ca.clinia.search.helper.deserialize
import ca.clinia.search.model.response.ResponseSearch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.json
import shouldEqual
import kotlin.test.Test

class TestResponseSearch {

    @Test
    fun parse() {
        @Serializable
        data class Example(val key: String)

        val response = ResponseSearch(
            recordsOrNull = listOf(
                ResponseSearch.Record(json { "key" to "value" }),
                ResponseSearch.Record(json { "key" to "value" })
            )
        )

        val examples = response.records.deserialize(Example.serializer())

        examples shouldEqual listOf(
            Example("value"),
            Example("value")
        )
    }
}