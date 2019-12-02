package ca.clinia.search.model.places

import ca.clinia.search.model.ID
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
    public val id: ID
        get() = idOrNull!!

    public val type: PlaceType
        get() = typeOrNull!!

    public val formattedAddress: String
        get() = formattedAddressOrNull!!

    public val suite: String
        get() = suiteOrNull!!

    public val route: String
        get() = routeOrNull!!

    public val postalCode: String
        get() = postalCodeOrNull!!

    public val neighborhood: String
        get() = neighborhoodOrNull!!

    public val locality: String
        get() = localityOrNull!!

    public val place: String
        get() = placeOrNull!!

    public val district: String
        get() = districtOrNull!!

    public val region: String
        get() = regionOrNull!!

    public val regionCode: String
        get() = regionCodeOrNull!!

    public val country: String
        get() = countryOrNull!!

    public val countryCode: String
        get() = countryCodeOrNull!!

    public val geometry: Geometry
        get() = geometryOrNull!!
}