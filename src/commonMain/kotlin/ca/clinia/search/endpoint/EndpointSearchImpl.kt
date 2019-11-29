package ca.clinia.search.endpoint

import ca.clinia.search.configuration.CallType
import ca.clinia.search.model.IndexName
import ca.clinia.search.model.multipleindex.IndexQuery
import ca.clinia.search.model.response.ResponseSearch
import ca.clinia.search.model.search.Query
import ca.clinia.search.serialize.*
import ca.clinia.search.transport.RequestOptions
import ca.clinia.search.transport.Transport
import io.ktor.http.HttpMethod


internal class EndpointSearchImpl(
    private val transport: Transport,
    override val indexName: IndexName
) : EndpointSearch {

    override suspend fun search(query: Query, requestOptions: RequestOptions?): ResponseSearch {
        val body = query.toBody()

        return transport.request(HttpMethod.Post, CallType.Read, indexName.toPath("/query"), requestOptions, body)
    }

    private fun Query.toIndexQuery(): IndexQuery {
        return IndexQuery(indexName, copy())
    }
}