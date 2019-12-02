package ca.clinia.search.endpoint

import ca.clinia.search.configuration.CallType
import ca.clinia.search.model.response.ResponseQuerySuggestions
import ca.clinia.search.model.suggest.SuggestionQuery
import ca.clinia.search.serialize.RouteIndexesV1
import ca.clinia.search.serialize.toBody
import ca.clinia.search.transport.RequestOptions
import ca.clinia.search.transport.Transport
import io.ktor.http.HttpMethod

internal class EndpointSuggestImpl(
    private val transport: Transport
) : EndpointSuggest {

    override suspend fun suggest(
        query: SuggestionQuery,
        requestOptions: RequestOptions?
    ): ResponseQuerySuggestions {
        val body = query.toBody()

        return transport.request(
            HttpMethod.Post,
            CallType.Read,
            "$RouteIndexesV1/suggestions/query",
            requestOptions,
            body
        )
    }
}