package ca.clinia.search.model.suggest

import ca.clinia.search.dsl.DSLParameters
import ca.clinia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@DSLParameters
class SuggestionQuery(
    /**
     * The text to search in the index.
     * Engine default: ""
     */
    @SerialName(KeyQuery) val query: String? = null,

    /**
     * Tag to put at the start of the highlighted match.
     */
    @SerialName(KeyHighlightPreTag) val highlightPreTag: String? = null,

    /**
     * Tag to put at the end of the highlighted match.
     */
    @SerialName(KeyHighlightPostTag) val highlightPostTag: String? = null,

    /**
     * Set the maximum number of suggestion to receive.
     */
    @SerialName(KeySize) val size: Int? = 5
)