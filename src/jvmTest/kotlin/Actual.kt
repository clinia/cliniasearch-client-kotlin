import ca.clinia.search.client.ClientSearch
import ca.clinia.search.configuration.Compression
import ca.clinia.search.configuration.ConfigurationSearch
import ca.clinia.search.helper.toAPIKey
import ca.clinia.search.helper.toApplicationID
import kotlinx.coroutines.CoroutineScope
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext

internal actual val clientSearch = ClientSearch(
    System.getenv("CLINIA_APPLICATION_ID_1").toApplicationID(),
    System.getenv("CLINIA_SEARCH_KEY_1").toAPIKey()
)
internal actual val clientAdmin1 = ClientSearch(
    System.getenv("CLINIA_APPLICATION_ID_1").toApplicationID(),
    System.getenv("CLINIA_ADMIN_KEY_1").toAPIKey()
)
internal actual val clientAdmin2 = ClientSearch(
    ConfigurationSearch(
        System.getenv("CLINIA_APPLICATION_ID_2").toApplicationID(),
        System.getenv("CLINIA_ADMIN_KEY_2").toAPIKey(),
        compression = Compression.None
    )
)
internal actual val clientMcm = ClientSearch(
    System.getenv("CLINIA_ADMIN_ID_MCM").toApplicationID(),
    System.getenv("CLINIA_ADMIN_KEY_MCM").toAPIKey()
)

internal actual val username: String
    get() {
        return try {
            System.getProperty("user.name")
        } catch (exception: Exception) {
            "unknown"
        }
    }

internal actual fun runBlocking(coroutineContext: CoroutineContext, block: suspend CoroutineScope.() -> Unit) {
    kotlinx.coroutines.runBlocking(coroutineContext, block = block)
}

internal actual object DateFormat {

    private val dateFormat = SimpleDateFormat("YYYY-MM-dd-HH-mm-ss").also {
        it.timeZone = TimeZone.getTimeZone("UTC")
    }

    actual fun format(timestamp: Long?): String {
        return dateFormat.format(if (timestamp != null) Date(timestamp) else Date())
    }

    actual fun parse(date: String): Long {
        return dateFormat.parse(date).time
    }
}

internal actual fun loadScratch(name: String): String {
    return if (File("src/commonTest/resources").exists()) {
        File("src/commonTest/resources/$name").readText()
    } else {
        File("../../src/commonTest/resources/$name").readText()
    }
}