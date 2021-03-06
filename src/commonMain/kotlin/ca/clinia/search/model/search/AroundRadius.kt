package ca.clinia.search.model.search

import ca.clinia.search.model.Raw
import ca.clinia.search.serialize.KeyAll
import ca.clinia.search.serialize.asJsonInput
import ca.clinia.search.serialize.asJsonOutput
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.content
import kotlinx.serialization.json.int
import kotlinx.serialization.json.intOrNull


/**
 * Define the maximum radius for a geo search (in meters).
 * This setting only works within the context of a radial (circular) geo search, enabled by aroundLatLngViaIP or
 * aroundLatLng.
 */
@Serializable(AroundRadius.Companion::class)
public sealed class AroundRadius(override val raw: String) : Raw<String> {

    /**
     *  Disables the radius logic, allowing all results to be returned, regardless of distance. =
     *  Ranking is still based on proximity to the central axis point. This option is faster than specifying a high integer value.
     */
    public object All : AroundRadius(KeyAll)

    /**
     * Integer value (in meters) representing the radius around the coordinates specified during the query.
     */
    public data class InMeters(val radius: Int) : AroundRadius(radius.toString())

    public data class Other(override val raw: String) : AroundRadius(raw)

    override fun toString(): String {
        return raw
    }

    @Serializer(AroundRadius::class)
    companion object : KSerializer<AroundRadius> {

        override fun serialize(encoder: Encoder, obj: AroundRadius) {
            val element = when (obj) {
                is InMeters -> JsonPrimitive(obj.radius)
                else -> JsonPrimitive(obj.raw)
            }

            encoder.asJsonOutput().encodeJson(element)
        }

        override fun deserialize(decoder: Decoder): AroundRadius {
            val element = decoder.asJsonInput()

            return when {
                element.intOrNull != null -> InMeters(element.int)
                else -> when (element.content) {
                    KeyAll -> All
                    else -> Other(element.content)
                }
            }
        }
    }
}