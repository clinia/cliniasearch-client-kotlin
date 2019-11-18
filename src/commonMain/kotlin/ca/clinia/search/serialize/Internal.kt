@file:Suppress("RegExpRedundantEscape")

package ca.clinia.search.serialize

import ca.clinia.search.model.multipleindex.IndexQuery
import ca.clinia.search.model.request.RequestMultipleQueries
import ca.clinia.search.model.search.Query
import io.ktor.http.Parameters
import io.ktor.http.formUrlEncode
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.json.*

internal val regexPlaceholder = Regex("^<(.*)>$")
internal val regexPoint = Regex("^(.*),(.*)$")

internal fun JsonObject.merge(jsonObject: JsonObject): JsonObject {
    return toMutableMap().run {
        putAll(jsonObject.content)
        JsonObject(this)
    }
}

internal fun JsonObject.urlEncode(): String? {
    return if (isNotEmpty()) {
        Parameters.build {
            entries.forEach { (key, element) ->
                when (element) {
                    is JsonPrimitive -> append(key, element.content)
                    else -> append(key, Json.stringify(JsonElementSerializer, element))
                }
            }
        }.formUrlEncode()
    } else null
}

internal fun Decoder.asJsonInput() = (this as JsonInput).decodeJson()
internal fun Encoder.asJsonOutput() = this as JsonOutput


internal fun Query.toJsonNoDefaults(): JsonObject {
    return JsonNoDefaults.toJson(Query.serializer(), this).jsonObject
}

internal val Json = Json(JsonConfiguration.Stable.copy())
internal val JsonNoDefaults = Json(JsonConfiguration.Stable.copy(encodeDefaults = false))
internal val JsonNonStrict = Json(JsonConfiguration.Stable.copy(strictMode = false))
internal val JsonDebug = Json(JsonConfiguration.Stable.copy(prettyPrint = true, indent = "  ", encodeDefaults = false))

internal fun List<IndexQuery>.toBody(): String {
    return JsonNoDefaults.stringify(
        RequestMultipleQueries,
        RequestMultipleQueries(this)
    )
}

internal fun Query.toBody(): String {
    return JsonNoDefaults.stringify(Query.serializer(), this)
}