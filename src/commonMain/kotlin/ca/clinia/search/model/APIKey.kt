package ca.clinia.search.model

import ca.clinia.search.exception.EmptyStringException
import ca.clinia.search.helper.toAPIKey
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer

/**
 * [APIKey] can't be a blank or empty string.
 */
@Serializable(APIKey.Companion::class)
public data class APIKey(override val raw: String) : Raw<String> {

    init {
        if (raw.isBlank()) throw EmptyStringException("APIKey")
    }

    override fun toString(): String {
        return raw
    }

    @Serializer(APIKey::class)
    companion object : KSerializer<APIKey> {

        private val serializer = StringSerializer

        override fun serialize(encoder: Encoder, obj: APIKey) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): APIKey {
            return serializer.deserialize(decoder).toAPIKey()
        }
    }
}