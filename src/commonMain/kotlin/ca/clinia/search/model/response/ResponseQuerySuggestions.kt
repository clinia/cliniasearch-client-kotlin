package ca.clinia.search.model.response

import ca.clinia.search.model.suggest.QuerySuggestion
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor

@Serializable
public data class ResponseQuerySuggestions(
    /**
     * List of suggestions.
     */
    val suggestions: List<QuerySuggestion>

) {
    @Serializer(ResponseQuerySuggestions::class)
    companion object : KSerializer<ResponseQuerySuggestions> {

        override val descriptor = StringDescriptor.withName("ResponseQuerySuggestions")

        override fun serialize(encoder: Encoder, obj: ResponseQuerySuggestions) {
            QuerySuggestion.serializer().list.serialize(encoder, obj.suggestions)
        }

        override fun deserialize(decoder: Decoder): ResponseQuerySuggestions {
            return ResponseQuerySuggestions(QuerySuggestion.serializer().list.deserialize(decoder))
        }
    }
}