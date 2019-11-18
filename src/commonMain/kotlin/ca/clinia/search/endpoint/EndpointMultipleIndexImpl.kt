package ca.clinia.search.endpoint

import ca.clinia.search.configuration.CallType
import ca.clinia.search.model.multipleindex.IndexQuery
import ca.clinia.search.model.response.*
import ca.clinia.search.serialize.*
import ca.clinia.search.transport.RequestOptions
import ca.clinia.search.transport.Transport
import io.ktor.http.HttpMethod


internal class EndpointMultipleIndexImpl(
    private val transport: Transport
) : EndpointMultipleIndex {


    override suspend fun multipleQueries(
        queries: List<IndexQuery>,
        requestOptions: RequestOptions?
    ): ResponseSearches {
        val body = queries.toBody()

        return transport.request(HttpMethod.Post, CallType.Read, "$RouteIndexesV1/*/queries", requestOptions, body)
    }
}