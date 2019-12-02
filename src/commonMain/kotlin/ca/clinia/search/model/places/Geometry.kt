package ca.clinia.search.model.places

import ca.clinia.search.model.search.BoundingBox
import ca.clinia.search.model.search.Point
import ca.clinia.search.serialize.*
import kotlinx.serialization.*

@Serializable
public data class Geometry(
    @SerialName(KeyLocation) val location: Point,
    @SerialName(KeyBounds) val bounds: BoundingBox
)