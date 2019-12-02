package ca.clinia.search.serialize

import ca.clinia.search.helper.and
import ca.clinia.search.model.search.BoundingBox
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.internal.StringSerializer

public object KSerializerBoundingBox : KSerializer<BoundingBox> {

    private val serializer = StringSerializer

    override val descriptor = serializer.descriptor

    override fun serialize(encoder: Encoder, obj: BoundingBox) {
        serializer.serialize(encoder, "${obj.northEast.lat},${obj.northEast.lng},${obj.southWest.lat},${obj.southWest.lng}")
    }

    override fun deserialize(decoder: Decoder): BoundingBox {
        val values = regexBoundingBox.find(serializer.deserialize(decoder))!!.groupValues

        return BoundingBox(values[1].toFloat() and values[2].toFloat(), values[3].toFloat() and values[4].toFloat())
    }
}