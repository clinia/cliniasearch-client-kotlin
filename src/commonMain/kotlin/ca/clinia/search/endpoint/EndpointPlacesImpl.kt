package ca.clinia.search.endpoint

import ca.clinia.search.configuration.CallType
import ca.clinia.search.model.multipleindex.IndexQuery
import ca.clinia.search.model.places.Place
import ca.clinia.search.model.places.PlaceType
import ca.clinia.search.model.places.PlacesQuery
import ca.clinia.search.model.response.ResponseSearch
import ca.clinia.search.model.response.ResponseSearchPlaces
import ca.clinia.search.model.search.Query
import ca.clinia.search.serialize.*
import ca.clinia.search.serialize.toBody
import ca.clinia.search.transport.RequestOptions
import ca.clinia.search.transport.Transport
import io.ktor.http.HttpMethod

internal class EndpointPlacesImpl(
    private val transport: Transport
) : EndpointPlaces {
    override suspend fun searchPlaces(query: PlacesQuery, requestOptions: RequestOptions?): ResponseSearchPlaces {
        val options = requestOptions ?: RequestOptions()

        options.parameter(KeyInput, if (query.input != null) query.input else "")
        options.parameter(KeyLimit, query.limit)

        val types = if (!query.types.isNullOrEmpty()) query.types else listOf(PlaceType.PostCode, PlaceType.Place, PlaceType.Neighborhood)
        for (type in types!!) {
            options.parameter(KeyTypes, type.raw)
        }

        if (!query.country.isNullOrEmpty()) {
            options.parameter(KeyCountry, query.country!!.joinToString(","))
        }

        return transport.request(HttpMethod.Get, CallType.Read, "$RouteLocations/autocomplete", options)
    }
}