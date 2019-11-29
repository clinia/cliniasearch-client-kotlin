package ca.clinia.search.model.places

import ca.clinia.search.model.ID

public interface Place {

    val idOrNull: ID?
    val typeOrNull: String?
    val formattedAddressOrNull: String?
    val suiteOrNull: String?
    val routeOrNull: String?
    val postalCodeOrNull: String?
    val neighborhoodOrNull: String?
    val localityOrNull: String?
    val placeOrNull: String?
    val districtOrNull: String?
    val regionOrNull: String?
    val regionCodeOrNull: String?
    val countryOrNull: String?
    val countryCodeOrNull: String?

    // TODO Geometry

}