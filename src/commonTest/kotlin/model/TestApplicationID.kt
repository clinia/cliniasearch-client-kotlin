package model

import ca.clinia.search.exception.EmptyStringException
import ca.clinia.search.model.ApplicationID
import shouldFailWith
import kotlin.test.Test

internal class TestApplicationID {

    @Test
    fun rawShouldNotBeEmpty() {
        shouldFailWith<EmptyStringException> { ApplicationID("") }
    }

    @Test
    fun rawShouldNotBeBlank() {
        shouldFailWith<EmptyStringException> { ApplicationID(" ") }
    }
}