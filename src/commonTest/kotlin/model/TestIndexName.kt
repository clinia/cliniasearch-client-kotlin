package model

import ca.clinia.search.exception.EmptyStringException
import ca.clinia.search.model.IndexName
import shouldEqual
import shouldFailWith
import kotlin.test.Test

internal class TestIndexName {

    @Test
    fun rawShouldNotBeEmpty() {
        shouldFailWith<EmptyStringException> { IndexName("") }
    }

    @Test
    fun rawShouldNotBeBlank() {
        shouldFailWith<EmptyStringException> { IndexName(" ") }
    }

    @Test
    fun pathIndexes() {
        val index = IndexName("somePath")

        index.toPath() shouldEqual "search/v1/indexes/somePath"
        index.toPath("/someSuffix") shouldEqual "search/v1/indexes/somePath/someSuffix"
    }
}