package ca.clinia.search.model.search

import ca.clinia.search.helper.and
import ca.clinia.search.model.Raw
import ca.clinia.search.serialize.KeyNorthEast
import ca.clinia.search.serialize.KeySouthWest
import kotlinx.serialization.*
import kotlinx.serialization.internal.FloatSerializer


/**
 * Search inside a rectangular area (in geo coordinates).
 * The rectangle is defined by two diagonally opposite points (hereafter [point1] and [point2]).
 */
@Serializable
public data class BoundingBox(
    @SerialName(KeyNorthEast) val northEast: Point,
    @SerialName(KeySouthWest) val southWest: Point
)