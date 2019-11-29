package ca.clinia.search.dsl

import ca.clinia.search.model.search.Query

/**
 * Create a [Query] with [block] and an optional [query]
 */
public fun query(query: String? = null, block: Query.() -> Unit): Query {
    return Query(query = query).apply(block)
}