package ca.clinia.search.transport

import ca.clinia.search.configuration.CallType
import ca.clinia.search.configuration.RetryableHost
import ca.clinia.search.model.ApplicationID
import ca.clinia.search.model.Time


internal val ApplicationID.searchHosts
    get() = listOf(
        RetryableHost("$this-dsn.algolia.net", CallType.Read),
        RetryableHost("$this.algolia.net", CallType.Write),
        RetryableHost("$this-1.algolianet.com"),
        RetryableHost("$this-2.algolianet.com"),
        RetryableHost("$this-3.algolianet.com")
    )

internal val placesHosts = listOf(
    RetryableHost("places-dsn.algolia.net"),
    RetryableHost("places-1.algolianet.com"),
    RetryableHost("places-2.algolianet.com"),
    RetryableHost("places-3.algolianet.com")
)

internal fun RetryableHost.reset() {
    lastUpdated = Time.getCurrentTimeMillis()
    isUp = true
    retryCount = 0
}

internal fun RetryableHost.hasTimedOut() {
    isUp = true
    lastUpdated = Time.getCurrentTimeMillis()
    retryCount += 1
}

internal fun RetryableHost.hasFailed() {
    isUp = false
    lastUpdated = Time.getCurrentTimeMillis()
}

internal fun List<RetryableHost>.expireHostsOlderThan(hostStatusExpirationDelayMS: Long) {
    forEach {
        val timeDelayExpired = Time.getCurrentTimeMillis() - it.lastUpdated
        if (timeDelayExpired > hostStatusExpirationDelayMS) {
            it.reset()
        }
    }
}

internal fun List<RetryableHost>.filterCallType(callType: CallType): List<RetryableHost> {
    return filter { it.callType == callType || it.callType == null }
}
