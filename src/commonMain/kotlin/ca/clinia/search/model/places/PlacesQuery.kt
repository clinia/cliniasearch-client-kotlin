package ca.clinia.search.model.places

import ca.clinia.search.dsl.DSLParameters
import ca.clinia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@DSLParameters
data class PlacesQuery(
    /**
     * The query to match places by name
     */
    @SerialName(KeyInput) var input: String? = null,

    /**
     * The type of places to look for
     */
    @SerialName(KeyTypes) var types: List<PlaceType>? = null,

    /**
     * The preferred language in which to get the results in.
     */
    @SerialName(KeyLocale) var country: List<String>? = null,

    /**
     * The maximum number of results to return
     */
    @SerialName(KeyLimit) var limit: Int? = 5
)