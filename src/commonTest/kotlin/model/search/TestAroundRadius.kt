package model.search

import ca.clinia.search.model.search.AroundRadius
import ca.clinia.search.serialize.KeyAll
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestAroundRadius {

    @Test
    fun raw() {
        AroundRadius.All.raw shouldEqual KeyAll
        AroundRadius.InMeters(10).raw shouldEqual "10"
        AroundRadius.Other(unknown).raw shouldEqual unknown
    }
}