package ca.clinia.search.model.request

import ca.clinia.search.model.multipleindex.IndexQuery
import ca.clinia.search.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray


@Serializable(RequestMultipleQueries.Companion::class)
internal class RequestMultipleQueries(
    @SerialName(KeyRequests) val indexQueries: List<IndexQuery>
) {

    @Serializer(RequestMultipleQueries::class)
    companion object : SerializationStrategy<RequestMultipleQueries> {

        override fun serialize(encoder: Encoder, obj: RequestMultipleQueries) {
            val json = json {
                KeyRequests to jsonArray {
                    obj.indexQueries.forEach {
                        +json {
                            KeyIndexName to it.indexName.raw
                            it.query.toJsonNoDefaults().urlEncode()?.let { KeyParams to it }
                        }
                    }
                }
            }
            encoder.asJsonOutput().encodeJson(json)
        }
    }
}