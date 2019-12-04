package ca.clinia.search.model.suggest

import ca.clinia.search.dsl.DSLParameters
import ca.clinia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@DSLParameters
class SuggestionQuery(
    /**
     * The query to get suggestion for.
     * Engine default: ""
     */
    @SerialName(KeyQuery) var query: String? = null,

    /**
     * Tag to put at the start of the highlighted match.
     */
    @SerialName(KeyHighlightPreTag) var highlightPreTag: String? = null,

    /**
     * Tag to put at the end of the highlighted match.
     */
    @SerialName(KeyHighlightPostTag) var highlightPostTag: String? = null,

    /**
     * Set the maximum number of suggestion to receive.
     */
    @SerialName(KeySize) var size: Int? = 5
)