package suite

import ca.clinia.search.helper.toIndexName
import ca.clinia.search.model.IndexName
import username

internal fun testSuiteIndexName(suffix: String): IndexName {
    val date = DateFormat.format()
    val prefix = "kotlin-$date"

    return "$prefix-$username-$suffix".toIndexName()
}