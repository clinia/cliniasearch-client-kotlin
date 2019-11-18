package ca.clinia.search.model.response

import ca.clinia.search.model.multipleindex.IndexQuery
import ca.clinia.search.serialize.KeyResults
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class ResponseSearches(
    /**
     * List of result in the order they were submitted, one element for each [IndexQuery].
     */
    @SerialName(KeyResults) val results: List<ResponseSearch>
)