package ca.clinia.search.helper


internal expect fun String.sha256(key: String): String

internal expect fun String.encodeBase64(): String

internal expect fun String.decodeBase64(): String