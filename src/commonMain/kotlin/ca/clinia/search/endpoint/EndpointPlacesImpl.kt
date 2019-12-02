package ca.clinia.search.endpoint

import ca.clinia.search.configuration.CallType
import ca.clinia.search.model.places.PlacesQuery
import ca.clinia.search.model.response.ResponseSearchPlaces
import ca.clinia.search.serialize.*
import ca.clinia.search.transport.RequestOptions
import ca.clinia.search.transport.Transport
import io.ktor.http.HttpMethod

internal class EndpointPlacesImpl(
    private val transport: Transport
) : EndpointPlaces {
    override suspend fun searchPlaces(query: PlacesQuery, requestOptions: RequestOptions?): ResponseSearchPlaces {
        val body =  query.toBody()

        return transport.request(HttpMethod.Post, CallType.Read, "$RouteLocation/autocomplete", body = body, requestOptions = null)
    }
}