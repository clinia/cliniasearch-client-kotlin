package model.response

import attributeA
import attributeB
import ca.clinia.search.model.response.ResponseSearch
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.json
import shouldEqual
import kotlin.test.Test

internal class TestResponseSearchRecord {

    @Serializable
    data class Sample(
        val attributeA: String,
        val attributeB: String
    )

    @Test
    fun dx() {
        val json = json {
            attributeA.raw to "valueA"
            attributeB.raw to "valueB"
        }
        val record = ResponseSearch.Record(json)

        record.getValue(StringSerializer, attributeA) shouldEqual "valueA"
        record.getValue(StringSerializer, attributeB) shouldEqual "valueB"
        record.deserialize(Sample.serializer()) shouldEqual Sample("valueA", "valueB")
    }
}