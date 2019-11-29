package ca.clinia.search.endpoint

import ca.clinia.search.model.places.PlacesQuery
import ca.clinia.search.transport.RequestOptions
import ca.clinia.search.transport.Transport

internal class EndpointPlacesImpl(
    private val transport: Transport
) : EndpointPlaces {



    override suspend fun searchPlaces(query: PlacesQuery, requestOptions: RequestOptions?) {
        // TODO
    }

}

