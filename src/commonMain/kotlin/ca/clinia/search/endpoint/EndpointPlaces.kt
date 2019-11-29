package ca.clinia.search.endpoint

import ca.clinia.search.model.places.Place
import ca.clinia.search.model.places.PlacesQuery
import ca.clinia.search.model.response.ResponseSearchPlaces
import ca.clinia.search.transport.RequestOptions

public interface EndpointPlaces {

    suspend fun searchPlaces(
        query: PlacesQuery = PlacesQuery(),
        requestOptions: RequestOptions? = null
    ) : ResponseSearchPlaces
}