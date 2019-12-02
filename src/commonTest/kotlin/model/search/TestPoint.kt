package model.search

import ca.clinia.search.helper.and
import ca.clinia.search.model.search.Point
import shouldEqual
import kotlin.test.Test

internal class TestPoint {

    @Test
    fun dx() {
        val point = 1f and 2f

        point shouldEqual Point(1f, 2f)
    }

    @Test
    fun point() {
        val point = Point(1f, 2f)

        1f shouldEqual point.lat
        2f shouldEqual point.lng
    }
}