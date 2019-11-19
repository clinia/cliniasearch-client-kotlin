package transport

import clientAdmin1
import ca.clinia.search.configuration.CallType
import ca.clinia.search.configuration.ConfigurationSearch
import ca.clinia.search.configuration.RetryableHost
import ca.clinia.search.transport.Transport
import runBlocking
import shouldEqual
import kotlin.test.Test


internal class TestCallableHosts {

    private val configuration = ConfigurationSearch(clientAdmin1.applicationID, clientAdmin1.apiKey)
    private val transport = Transport(configuration, null)
    private val hostRead = RetryableHost("${configuration.applicationID}-dsn.clinia.net", CallType.Read)
    private val hostWrite = RetryableHost("${configuration.applicationID}.clinia.net", CallType.Write)
    private val hostFallback1 = RetryableHost("${configuration.applicationID}-1.clinianet.com")
    private val hostFallback2 = RetryableHost("${configuration.applicationID}-2.clinianet.com")
    private val hostfallback3 = RetryableHost("${configuration.applicationID}-3.clinianet.com")

    @Test
    fun callTypeRead() {
        runBlocking {
            val hosts = transport.callableHosts(CallType.Read)

            hosts shouldEqual listOf(
                hostRead,
                hostFallback1,
                hostFallback2,
                hostfallback3
            )
        }
    }

    @Test
    fun callTypeWrite() {
        runBlocking {
            val hosts = transport.callableHosts(CallType.Write)

            hosts shouldEqual listOf(
                hostWrite,
                hostFallback1,
                hostFallback2,
                hostfallback3
            )
        }
    }

    @Test
    fun onlyFirstIsUp() {
        runBlocking {
            transport.hosts.forEach { it.isUp = false }
            transport.hosts.first().isUp = true

            val hosts = transport.callableHosts(CallType.Read)

            hosts shouldEqual listOf(hostRead)
        }
    }

    @Test
    fun onlyLastIsUp() {
        runBlocking {
            transport.hosts.forEach { it.isUp = false }
            transport.hosts.last().isUp = true

            val hosts = transport.callableHosts(CallType.Read)

            hosts shouldEqual listOf(hostfallback3)
        }
    }

    @Test
    fun noneAreUp() {
        runBlocking {
            transport.hosts.forEach { it.isUp = false }

            val hosts = transport.callableHosts(CallType.Write)

            hosts shouldEqual listOf(
                hostWrite,
                hostFallback1,
                hostFallback2,
                hostfallback3
            )
        }
    }
}