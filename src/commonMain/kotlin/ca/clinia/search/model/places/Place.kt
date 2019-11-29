package ca.clinia.search.model.places

import ca.clinia.search.model.ID
import ca.clinia.search.model.search.Point
import ca.clinia.search.model.search.Query
import ca.clinia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Place(
    @SerialName(KeyID) val idOrNull: ID? = null,
    @SerialName(KeyType) val typeOrNull: PlaceType? = null,
    @SerialName(KeyFormattedAddress) val formattedAddressOrNull: String? = null,
    @SerialName(KeySuite) val suiteOrNull: String? = null,
    @SerialName(KeyRoute) val routeOrNull: String? = null,
    @SerialName(KeyPostalCode) val postalCodeOrNull: String? = null,
    @SerialName(KeyNeighborhood) val neighborhoodOrNull: String? = null,
    @SerialName(KeyLocality) val localityOrNull: String? = null,
    @SerialName(KeyPlace) val placeOrNull: String? = null,
    @SerialName(KeyDistrict) val districtOrNull: String? = null,
    @SerialName(KeyRegion) val regionOrNull: String? = null,
    @SerialName(KeyRegionCode) val regionCodeOrNull: String? = null,
    @SerialName(KeyCountry) val countryOrNull: String? = null,
    @SerialName(KeyCountryCode) val countryCodeOrNull: String? = null,
    @SerialName(KeyGeometry) val geometryOrNull: Geometry? = null
    ) {
    public val Id: ID
        get() = idOrNull!!

    public val Type: PlaceType
        get() = typeOrNull!!

    public val FormattedAddress: String
        get() = formattedAddressOrNull!!

    public val Suite: String
        get() = suiteOrNull!!

    public val Route: String
        get() = routeOrNull!!

    public val PostalCode: String
        get() = postalCodeOrNull!!

    public val Neighborhood: String
        get() = neighborhoodOrNull!!

    public val Locality: String
        get() = localityOrNull!!

    public val Place: String
        get() = placeOrNull!!

    public val District: String
        get() = districtOrNull!!

    public val Region: String
        get() = regionOrNull!!

    public val RegionCode: String
        get() = regionCodeOrNull!!

    public val Country: String
        get() = countryOrNull!!

    public val CountryCode: String
        get() = countryCodeOrNull!!

    public val Geometry: Geometry
        get() = geometryOrNull!!
}