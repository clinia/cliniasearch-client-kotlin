package ca.clinia.search.client

import ca.clinia.search.configuration.Configuration
import ca.clinia.search.configuration.ConfigurationPlaces
import ca.clinia.search.configuration.CredentialsImpl
import ca.clinia.search.endpoint.EndpointPlaces
import ca.clinia.search.endpoint.EndpointPlacesImpl
import ca.clinia.search.model.APIKey
import ca.clinia.search.model.ApplicationID
import ca.clinia.search.transport.Transport

public class ClientPlaces private constructor(
    private val transport: Transport
) : EndpointPlaces by EndpointPlacesImpl(transport),
        Configuration by transport {

    public constructor(
        applicationID: ApplicationID,
        apiKey: APIKey
    ) : this(
        Transport(ConfigurationPlaces(), CredentialsImpl(applicationID, apiKey))
    )

    public constructor(
        configuration: ConfigurationPlaces
    ) : this(Transport(configuration, null))

    public constructor() : this(Transport(ConfigurationPlaces(), null))
}

