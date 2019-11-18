package ca.clinia.search.client

import ca.clinia.search.endpoint.*
import ca.clinia.search.model.IndexName
import ca.clinia.search.model.response.ResponseSearch
import ca.clinia.search.model.search.Query
import ca.clinia.search.transport.RequestOptions
import ca.clinia.search.transport.Transport


/**
 * The main entry point for performing operations on a single index.
 */
public data class Index internal constructor(
    internal val transport: Transport,
    override val indexName: IndexName
) : EndpointSearch by EndpointSearchImpl(transport, indexName),
    EndpointAdvanced by EndpointAdvancedImpl(transport, indexName) {

}