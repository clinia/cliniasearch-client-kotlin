package ca.clinia.search.dsl.geosearch

import ca.clinia.search.dsl.DSL
import ca.clinia.search.dsl.DSLParameters
import ca.clinia.search.model.search.BoundingBox

/**
 * DSL for building a [List] of [BoundingBox]
 */
@DSLParameters
public class DSLBoundingBox (
    private val boundingBoxes: MutableList<BoundingBox> = mutableListOf()
) {

    /**
     * Add [this] to [boundingBoxes]
     */
    operator fun BoundingBox.unaryPlus() {
        boundingBoxes += this
    }

    companion object : DSL<DSLBoundingBox, List<BoundingBox>> {

        override operator fun invoke(block: DSLBoundingBox.() -> Unit): List<BoundingBox> {
            return DSLBoundingBox().apply(block).boundingBoxes
        }
    }
}