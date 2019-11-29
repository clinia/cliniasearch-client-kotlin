package ca.clinia.search.model.places

import ca.clinia.search.model.Raw
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer

@Serializable(PlaceType.Companion::class)
sealed class PlaceType(override val raw: String) : Raw<String> {

    companion object : KSerializer<PlaceType> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: PlaceType) {
            serializer.serialize(encoder, obj.raw)
        }

//        override fun deserialize(decoder: Decoder): PlaceType {
//            return when(val string = serializer.deserialize(decoder)) {
//                KeyPostCode ->
//            }
//        }
    }
}