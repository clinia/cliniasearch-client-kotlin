package transport

import ca.clinia.search.configuration.CallType
import ca.clinia.search.configuration.RetryableHost
import ca.clinia.search.helper.toApplicationID
import ca.clinia.search.transport.hasFailed
import ca.clinia.search.transport.hasTimedOut
import ca.clinia.search.transport.reset
import ca.clinia.search.transport.searchHosts
import shouldEqual
import kotlin.test.Test


internal class TestRetryableHost {

    private val host = RetryableHost("url", CallType.Write)
    private val applicationID = "appID".toApplicationID()

    @Test
    fun searchHosts() {
        applicationID.searchHosts shouldEqual listOf(
            RetryableHost("$applicationID-dsn.clinia.net", CallType.Read),
            RetryableHost("$applicationID.clinia.net", CallType.Write),
            RetryableHost("$applicationID-1.clinianet.com"),
            RetryableHost("$applicationID-2.clinianet.com"),
            RetryableHost("$applicationID-3.clinianet.com")
        )
    }

    @Test
    fun reset() {
        host.apply {
            isUp = false
            retryCount = 1
        }
        host.reset()
        host.let {
            it.isUp shouldEqual true
            it.retryCount shouldEqual 0
        }
    }

    @Test
    fun timedOut() {
        host.apply {
            isUp = false
            retryCount = 0
        }
        host.hasTimedOut()
        host.let {
            it.isUp shouldEqual true
            it.retryCount shouldEqual 1
        }
    }

    @Test
    fun failed() {
        host.apply {
            isUp = true
            retryCount = 1
        }
        host.hasFailed()
        host.let {
            it.isUp shouldEqual false
            it.retryCount shouldEqual 1
        }
    }
}