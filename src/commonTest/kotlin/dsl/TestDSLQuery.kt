package dsl

import ca.clinia.search.dsl.insideBoundingBox
import ca.clinia.search.dsl.query
import ca.clinia.search.helper.and
import ca.clinia.search.model.search.BoundingBox
import shouldNotBeNull
import kotlin.test.Test

internal class TestDSLQuery {

    @Test
    fun insideBoundingBox() {
        val query = query {
            insideBoundingBox {
                +BoundingBox(0f and 1f, 2f and 3f)
            }
        }

        query.insideBoundingBox.shouldNotBeNull()
    }
}