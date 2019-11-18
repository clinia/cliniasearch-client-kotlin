package ca.clinia.search.model.search

import ca.clinia.search.dsl.DSLParameters
import ca.clinia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@DSLParameters
public data class Query(
    /**
     * The text to search in the index.
     * Engine default: ""
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/query/?language=kotlin]
     */
    @SerialName(KeyQuery) var query: String? = null,

    /**
     * Specify the page to retrieve.
     * Engine default: 0
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/page/?language=kotlin]
     */
    @SerialName(KeyPage) var page: Int? = null,

    /**
     * Set the number of hits per page.
     * Engine default: 20
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/hitsPerPage/?language=kotlin]
     */
    @SerialName(KeyHitsPerPage) var hitsPerPage: Int? = null,

    /**
     * Search for entries around a central geolocation, enabling a geo search within a circular area.
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundLatLng/?language=kotlin]
     */
    @SerialName(KeyAroundLatLng) @Serializable(KSerializerPoint::class) var aroundLatLng: Point? = null,

    /**
     * Define the maximum radius for a geo search (in meters).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundRadius/?language=kotlin]
     */
    @SerialName(KeyAroundRadius) var aroundRadius: AroundRadius? = null,

    /**
     * Search inside a rectangular area (in geo coordinates).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/insideBoundingBox/?language=kotlin]
     */
    @SerialName(KeyInsideBoundingBox) var insideBoundingBox: List<BoundingBox>? = null,

    /**
     * Controls if and how query words are interpreted as [prefixes][https://www.algolia.com/doc/guides/textual-relevance/prefix-search/?language=kotlin].
     * Engine default: [QueryType.PrefixLast]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/queryType/?language=kotlin]
     */
    @SerialName(KeyQueryType) var queryType: QueryType? = null
)