package ca.clinia.search.model.response

import ca.clinia.search.model.multipleindex.IndexQuery
import ca.clinia.search.model.places.Place
import ca.clinia.search.model.places.PlaceType
import ca.clinia.search.serialize.KeyResults
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

        override fun serialize(output: Encoder, obj: ResponseSearchPlaces) {
            Place.serializer().list.serialize(output, obj.suggestions)
        }

        override fun deserialize(input: Decoder): ResponseSearchPlaces {
            return ResponseSearchPlaces(Place.serializer().list.deserialize(input))
        }
    }
}