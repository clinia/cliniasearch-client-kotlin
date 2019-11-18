package ca.clinia.search.dsl

import ca.clinia.search.model.Attribute
import ca.clinia.search.transport.RequestOptions

public val all = Attribute("*")

@DslMarker
public annotation class DSLParameters

interface DSL<T, S> : (T.() -> Unit) -> S

/**
 * Create a [RequestOptions] with [block]. Can take an optional [requestOptions] to be modified.
 */
public fun requestOptions(
    requestOptions: RequestOptions? = null,
    block: RequestOptions.() -> Unit
): RequestOptions {
    return (requestOptions ?: RequestOptions()).apply(block)
}

internal fun requestOptionsBuilder(
    requestOptions: RequestOptions? = null,
    block: RequestOptions.() -> Unit
) = requestOptions(requestOptions, block)