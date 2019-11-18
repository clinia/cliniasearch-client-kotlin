package ca.clinia.search.model


internal expect object Time {

    fun getCurrentTimeMillis(): Long
}