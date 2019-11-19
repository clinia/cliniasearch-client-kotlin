package ca.clinia.search.model.response

import ca.clinia.search.model.Attribute
import ca.clinia.search.model.IndexName
import ca.clinia.search.model.ID
import ca.clinia.search.model.search.*
import ca.clinia.search.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject


@Serializable
public data class ResponseSearch(
    /**
     * The hits returned by the search. Hits are ordered according to the ranking or sorting of the index being queried.
     * Hits are made of the schemaless JSON objects that you stored in the index.
     */
    @SerialName(KeyRecords) val recordsOrNull: List<Record>? = null,
    /**
     * The number of hits matched by the query.
     */
    @SerialName(KeyTotal) val totalOrNull: Int? = null,
    /**
     * Index of the current page (zero-based). See the [Query.page] search parameter.
     * Not returned if you use offset/length for pagination.
     */
    @SerialName(KeyPage) val pageOrNull: Int? = null,
    /**
     * The maximum number of hits returned per page. See the [Query.PerPage] search parameter.
     * Not returned if you use offset & length for pagination.
     */
    @SerialName(KeyPerPage) val perPageOrNull: Int? = null,
    /**
     * The number of returned pages. Calculation is based on the total number of hits (nbHits) divided by the number of
     * hits per page (hitsPerPage), rounded up to the nearest integer.
     * Not returned if you use offset & length for pagination.
     */
    @SerialName(KeyNumPages) val numPagesOrNull: Int? = null,
    /**
     * An echo of the query text. See the [Query.query] search parameter.
     */
    @SerialName(KeyQuery) val queryOrNull: String? = null,
    /**
     * A url-encoded string of all [Query] parameters.
     */
    @SerialName(KeyParams) val paramsOrNull: String? = null,
    /**
     * The computed geo location.
     * Only returned when [Query.aroundLatLngViaIP] or [Query.aroundLatLng] is set.
     */
    @SerialName(KeyAroundLatLng) @Serializable(KSerializerPoint::class) val aroundLatLngOrNull: Point? = null,
    /**
     * The automatically computed radius. For legacy reasons, this parameter is a string and not an integer.
     * Only returned for geo queries without an explicitly specified [Query.aroundRadius].
     */
    @SerialName(KeyAutomaticRadius) val automaticRadiusOrNull: Float? = null,

    @SerialName(KeyIndex) val indexNameOrNull: IndexName? = null
    ) {

    public val records: List<Record>
        get() = recordsOrNull!!

    public val total: Int
        get() = totalOrNull!!

    public val page: Int
        get() = pageOrNull!!

    public val perPage: Int
        get() = perPageOrNull!!

    public val numPages: Int
        get() = numPagesOrNull!!

    public val query: String
        get() = queryOrNull!!

    public val params: String
        get() = paramsOrNull!!

    public val aroundLatLng: Point
        get() = aroundLatLngOrNull!!

    public val automaticRadius: Float
        get() = automaticRadiusOrNull!!

    /**
     * Returns the position (0-based) within the [records] result list of the record matching against the given [recordID].
     * If the [recordID] is not found, -1 is returned.
     */
    public fun getRecordPosition(recordID: ID): Int {
        return records.indexOfFirst { it.json.getPrimitiveOrNull("id")?.content == recordID.raw }
    }

    /**
     * A Hit returned by the search.
     */
    @Serializable(Record.Companion::class)
    public data class Record(
        val json: JsonObject
    ) : Map<String, JsonElement> by json {

        /**
         * Deserialize the value of an [Attribute] to [T].
         */
        public fun <T> getValue(serializer: KSerializer<T>, attribute: Attribute): T {
            return Json.fromJson(serializer, json.getAs(attribute.raw))
        }

        /**
         * Deserialize the entire [json] to [T].
         */
        public fun <T> deserialize(deserializer: DeserializationStrategy<T>): T {
            return JsonNonStrict.fromJson(deserializer, json)
        }

        @Serializer(Record::class)
        companion object : KSerializer<Record> {

            override fun deserialize(decoder: Decoder): Record {
                return Record(decoder.asJsonInput().jsonObject)
            }

            override fun serialize(encoder: Encoder, obj: Record) {
                encoder.asJsonOutput().encodeJson(obj.json)
            }

        }
    }
}