package model.search

import ca.clinia.search.helper.and
import ca.clinia.search.model.search.BoundingBox
import shouldEqual
import kotlin.test.Test

internal class TestBoundingBox {

    @Test
    fun boundingBox() {
        val boundingBox = BoundingBox(1f and 2f, 3f and 4f)

        boundingBox.point1 shouldEqual (1f and 2f)
        boundingBox.point2 shouldEqual (3f and 4f)
        boundingBox.raw shouldEqual listOf(1f, 2f, 3f, 4f)
    }
}