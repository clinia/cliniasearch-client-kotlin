package ca.clinia.search.model.request

import ca.clinia.search.serialize.KeyParams
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
internal data class RequestParams(
    @SerialName(KeyParams) val params: String? = null
)