package ca.clinia.search.model.places

import ca.clinia.search.model.search.Point
import ca.clinia.search.serialize.*
import kotlinx.serialization.*

@Serializable
public data class Geometry(
    @SerialName(KeyLocation) val location: Coordinates,
    @SerialName(KeyBounds) val bounds: Bounds
)

@Serializable
public data class Bounds(
    @SerialName(KeyNorthEast) val northEast: Coordinates,
    @SerialName(KeySouthWest) val southWest: Coordinates
)

@Serializable
public data class Coordinates(
    @SerialName(KeyLat) val lat: Float,
    @SerialName(KeyLng) val lng: Float
)