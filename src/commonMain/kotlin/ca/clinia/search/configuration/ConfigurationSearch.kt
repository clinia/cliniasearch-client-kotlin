package ca.clinia.search.configuration

import ca.clinia.search.client.ClientSearch
import ca.clinia.search.model.APIKey
import ca.clinia.search.model.ApplicationID
import ca.clinia.search.transport.searchHosts
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.logging.LogLevel


/**
 * Configuration used by [ClientSearch].
 */
public data class ConfigurationSearch(
    override val applicationID: ApplicationID,
    override val apiKey: APIKey,
    override val writeTimeout: Long = defaultWriteTimeout,
    override val readTimeout: Long = defaultReadTimeout,
    override val logLevel: LogLevel = defaultLogLevel,
    override val hosts: List<RetryableHost> = applicationID.searchHosts,
    override val defaultHeaders: Map<String, String>? = null,
    override val engine: HttpClientEngine? = null,
    override val httpClientConfig: (HttpClientConfig<*>.() -> Unit)? = null,
    override val compression: Compression = Compression.None
) : Configuration, Credentials {

    override val httpClient = getHttpClient()
}