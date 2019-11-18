package ca.clinia.search.model

import ca.clinia.search.exception.EmptyStringException
import ca.clinia.search.helper.toRecordID
import ca.clinia.search.serialize.KeyID
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer


/**
 * Identifies a record uniquely.
 */
@Serializable(ID.Companion::class)
public data class ID(@SerialName(KeyID) override val raw: String) : Raw<String> {

    init {
        if (raw.isBlank()) throw EmptyStringException(KeyID)
    }

    override fun toString(): String {
        return raw
    }

    companion object : KSerializer<ID> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: ID) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): ID {
            return serializer.deserialize(decoder).toRecordID()
        }
    }
}