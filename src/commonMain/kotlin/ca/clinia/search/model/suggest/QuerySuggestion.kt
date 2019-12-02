package ca.clinia.search.model.suggest

import ca.clinia.search.serialize.KeyFacet
import ca.clinia.search.serialize.KeyHighlight
import ca.clinia.search.serialize.KeySuggestion
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class QuerySuggestion(
    @SerialName(KeySuggestion) val suggestion: String? = null,
    @SerialName(KeyFacet) val facet: String? = null,
    @SerialName(KeyHighlight) val highlight: String? = null
)