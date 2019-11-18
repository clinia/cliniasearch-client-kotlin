package ca.clinia.search.helper

import ca.clinia.search.model.response.ResponseSearch
import kotlinx.serialization.DeserializationStrategy


/**
 * Convenience method to transform all [ResponseSearch.Record.json] to a typed object [T] with [serializer].
 */
fun <T> List<ResponseSearch.Record>.deserialize(deserializer: DeserializationStrategy<T>): List<T> {
    return map { it.deserialize(deserializer) }
}