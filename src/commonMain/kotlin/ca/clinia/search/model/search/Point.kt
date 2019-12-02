package ca.clinia.search.model.search

import ca.clinia.search.model.Raw
import ca.clinia.search.serialize.KeyLat
import ca.clinia.search.serialize.KeyLng
import kotlinx.serialization.*
import kotlinx.serialization.internal.FloatSerializer


/**
 * A set of geo-coordinates [latitude] and [longitude].
 */
@Serializable
public data class Point(
    @SerialName(KeyLat) val lat: Float,
    @SerialName(KeyLng) val lng: Float
)