package ca.clinia.search.model.places

import ca.clinia.search.dsl.DSLParameters
import ca.clinia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
@DSLParameters
data class PlacesQuery(
    /**
     * The query to match places by name
     */
    @SerialName(KeyQuery) var query: String? = null,

    /**
     * The type of places to look for
     */
    @SerialName(KeyTypes) var types: List<PlaceType>? = listOf(PlaceType.PostCode, PlaceType.Place, PlaceType.Neighborhood),

    /**
     * The preferred language in which to get the results in.
     */
    @SerialName(KeyCountry) var country: List<String>? = null,

    /**
     * The maximum number of results to return
     */
    @SerialName(KeySize) var size: Int? = 5,

    /**
     * The preferred locale in which to receive suggestions.
     */
    @Transient var locale: String? = null
)