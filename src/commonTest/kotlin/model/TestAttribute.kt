package model

import ca.clinia.search.exception.EmptyStringException
import ca.clinia.search.model.Attribute
import shouldFailWith
import kotlin.test.Test

internal class TestAttribute {

    @Test
    fun rawShouldNotBeEmpty() {
        shouldFailWith<EmptyStringException> { Attribute("") }
    }

    @Test
    fun rawShouldNotBeBlank() {
        shouldFailWith<EmptyStringException> { Attribute(" ") }
    }
}