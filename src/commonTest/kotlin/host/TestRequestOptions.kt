package host

import ca.clinia.search.dsl.requestOptions
import ca.clinia.search.serialize.KeyForwardedFor
import shouldEqual
import kotlin.test.Test

internal class TestRequestOptions {

    @Test
    fun headers() {
        val requestOptions = requestOptions {
            headers["keyA"] = "valueA"
            headers["keyB"] = "valueB"
        }

        requestOptions.headers shouldEqual mutableMapOf("keyA" to "valueA", "keyB" to "valueB")
    }

    @Test
    fun parameters() {
        val requestOptions = requestOptions {
            urlParameters["keyA"] = "valueA"
            urlParameters["keyB"] = "valueB"
        }

        requestOptions.urlParameters shouldEqual mutableMapOf("keyA" to "valueA", "keyB" to "valueB")
    }

    @Test
    fun forwarded() {
        val requestOptions = requestOptions {
            headerForwardedFor("value")
        }

        requestOptions.headers shouldEqual mutableMapOf(KeyForwardedFor to "value")
    }
}