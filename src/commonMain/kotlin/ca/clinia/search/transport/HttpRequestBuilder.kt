package ca.clinia.search.transport

import ca.clinia.search.model.APIKey
import ca.clinia.search.model.ApplicationID
import ca.clinia.search.serialize.KeyCliniaAPIKey
import ca.clinia.search.serialize.KeyCliniaApplicationID
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.parameter


internal fun HttpRequestBuilder.setApplicationId(applicationID: ApplicationID?) {
    header(KeyCliniaApplicationID, applicationID?.raw)
}

internal fun HttpRequestBuilder.setApiKey(apiKey: APIKey?) {
    header(KeyCliniaAPIKey, apiKey?.raw)
}

internal fun HttpRequestBuilder.setRequestOptions(requestOptions: RequestOptions?) {
    requestOptions?.headers?.forEach { header(it.key, it.value) }
    requestOptions?.urlParameters?.forEach { parameter(it.key, it.value) }
    requestOptions?.body?.let { body = it }
}