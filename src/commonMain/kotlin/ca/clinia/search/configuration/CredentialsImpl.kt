package ca.clinia.search.configuration

import ca.clinia.search.model.APIKey
import ca.clinia.search.model.ApplicationID


internal data class CredentialsImpl(
    override val applicationID: ApplicationID,
    override val apiKey: APIKey
): Credentials