package serialize

import ca.clinia.search.serialize.*
import shouldEqual
import kotlin.test.Test

internal class TestKeys {

    @Test
    fun keys() {
        KeyQuery shouldEqual "query"
        KeyPage shouldEqual "page"
        KeyPerPage shouldEqual "perPage"
        KeyAroundLatLng shouldEqual "aroundLatLng"
        KeyAroundRadius shouldEqual "aroundRadius"
        KeyInsideBoundingBox shouldEqual "insideBoundingBox"
        KeyQueryType shouldEqual "queryType"
        KeyAll shouldEqual "all"
        KeyPrefixLast shouldEqual "prefix_last"
        KeyPrefixNone shouldEqual "prefix_none"
        KeyRecords shouldEqual "records"
        KeyIndex shouldEqual "index"
        KeyTotal shouldEqual "total"
        KeyNumPages shouldEqual "numPages"
        KeyParams shouldEqual "params"
        KeyID shouldEqual "id"
        KeyResults shouldEqual "results"
        KeyForwardedFor shouldEqual "X-Forwarded-For"
    }
}