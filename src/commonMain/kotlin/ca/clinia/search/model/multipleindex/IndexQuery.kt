package ca.clinia.search.model.multipleindex

import ca.clinia.search.endpoint.EndpointMultipleIndex
import ca.clinia.search.model.IndexName
import ca.clinia.search.model.search.Query
import kotlinx.serialization.Serializable

/**
 * Associate a [Query] to a specific [IndexName]. Used by [EndpointMultipleIndex.multipleQueries].
 */
@Serializable
public data class IndexQuery(
    val indexName: IndexName,
    val query: Query = Query()
)