package model.search

import ca.clinia.search.model.search.QueryType
import ca.clinia.search.serialize.KeyPrefixLast
import ca.clinia.search.serialize.KeyPrefixNone
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestQueryType {

    @Test
    fun raw() {
        QueryType.PrefixLast.raw shouldEqual KeyPrefixLast
        QueryType.PrefixNone.raw shouldEqual KeyPrefixNone
        QueryType.Other(unknown).raw shouldEqual unknown
    }
}