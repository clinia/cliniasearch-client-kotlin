package model

import ca.clinia.search.exception.EmptyStringException
import ca.clinia.search.model.ID
import shouldFailWith
import kotlin.test.Test

internal class TestID {

    @Test
    fun rawShouldNotBeEmpty() {
        shouldFailWith<EmptyStringException> { ID("") }
    }

    @Test
    fun rawShouldNotBeBlank() {
        shouldFailWith<EmptyStringException> { ID(" ") }
    }
}