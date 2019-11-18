package ca.clinia.search.helper


internal expect object DateISO8601 {

    fun format(timestamp: Long, inMilliseconds: Boolean = false): String

    fun parse(date: String, inMilliseconds: Boolean = false): Long
}