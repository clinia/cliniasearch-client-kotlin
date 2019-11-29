package ca.clinia.search.model.places

import ca.clinia.search.dsl.DSLParameters
import ca.clinia.search.serialize.KeyQuery
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@DSLParameters
data class PlacesQuery(
    /**
     * The query to match places by name
     */
    @SerialName(KeyQuery) var query: String? = null
)