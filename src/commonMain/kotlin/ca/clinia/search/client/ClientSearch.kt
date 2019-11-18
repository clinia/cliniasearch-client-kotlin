package ca.clinia.search.client


import ca.clinia.search.configuration.*
import ca.clinia.search.endpoint.*
import ca.clinia.search.model.*
import ca.clinia.search.transport.Transport
import io.ktor.client.features.logging.LogLevel


/**
 * Client to perform operations on indices.
 */
public class ClientSearch private constructor(
    internal val transport: Transport
) :
    EndpointMultipleIndex by EndpointMultipleIndexImpl(transport),
    Configuration by transport,
    Credentials by transport.credentials {

    public constructor(
        applicationID: ApplicationID,
        apiKey: APIKey,
        logLevel: LogLevel = defaultLogLevel
    ) : this(
        Transport(
            ConfigurationSearch(applicationID, apiKey, logLevel = logLevel),
            CredentialsImpl(applicationID, apiKey)
        )
    )

    public constructor(
        configuration: ConfigurationSearch
    ) : this(Transport(configuration, configuration))

    /**
     *  Initialize an [Index] configured with [ConfigurationSearch].
     */
    public fun initIndex(indexName: IndexName): Index {
        return Index(transport, indexName)
    }
}