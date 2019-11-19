import ca.clinia.search.client.ClientSearch
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

internal expect val clientSearch: ClientSearch
internal expect val clientAdmin1: ClientSearch
internal expect val clientAdmin2: ClientSearch

internal expect fun runBlocking(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
    block: suspend CoroutineScope.() -> Unit
)

internal expect fun loadScratch(name: String): String

internal expect val username: String

internal const val dayInMillis = 24 * 60 * 60 * 1000

internal expect object DateFormat {

    fun format(timestamp: Long? = null): String

    fun parse(date: String): Long
}