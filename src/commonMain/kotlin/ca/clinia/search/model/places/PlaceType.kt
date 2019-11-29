package ca.clinia.search.model.places

import ca.clinia.search.model.Raw
import ca.clinia.search.serialize.*
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer

@Serializable(PlaceType.Companion::class)
sealed class PlaceType(override val raw: String) : Raw<String> {

    public object Country : PlaceType(KeyCountry)
    public object Region : PlaceType(KeyRegion)
    public object PostCode : PlaceType(KeyPostCode)
    public object District : PlaceType(KeyDistrict)
    public object Place : PlaceType(KeyPlace)
    public object Locality : PlaceType(KeyLocality)
    public object Neighborhood : PlaceType(KeyNeighborhood)
    public object Address : PlaceType(KeyAddress)
    public object Poi : PlaceType(KeyPoi)
    public object Route : PlaceType(KeyRoute)

    public data class Other(override val raw: String) : PlaceType(raw)

    companion object : KSerializer<PlaceType> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: PlaceType) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): PlaceType {
            return when(val string = serializer.deserialize(decoder)) {
                KeyCountry -> Country
                KeyRegion -> Region
                KeyPostCode -> PostCode
                KeyDistrict -> District
                KeyPlace -> Place
                KeyLocality -> Locality
                KeyNeighborhood -> Neighborhood
                KeyAddress -> Address
                KeyPoi -> Poi
                KeyRoute -> Route
                else -> Other(string)
            }
        }
    }
}