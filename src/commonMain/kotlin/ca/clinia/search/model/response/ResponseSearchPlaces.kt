package ca.clinia.search.model.response

import ca.clinia.search.model.places.Place
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor

@Serializable
public data class ResponseSearchPlaces(
    /**
     * List of suggestions.
     */
    val suggestions: List<Place>

) {
    @Serializer(ResponseSearchPlaces::class)
    companion object : KSerializer<ResponseSearchPlaces> {

        override val descriptor = StringDescriptor.withName("ResponseSearchPlaces")

        override fun serialize(encoder: Encoder, obj: ResponseSearchPlaces) {
            Place.serializer().list.serialize(encoder, obj.suggestions)
        }

        override fun deserialize(decoder: Decoder): ResponseSearchPlaces {
            return ResponseSearchPlaces(Place.serializer().list.deserialize(decoder))
        }
    }
}