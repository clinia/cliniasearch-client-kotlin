package serialize

import ca.clinia.search.serialize.RouteIndexesV1
import shouldEqual
import kotlin.test.Test

internal class TestRoutes {

    @Test
    fun test() {
        RouteIndexesV1 shouldEqual "search/v1/indexes"
    }
}