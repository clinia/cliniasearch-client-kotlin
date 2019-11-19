package ca.clinia.search.endpoint

import ca.clinia.search.model.multipleindex.IndexQuery
import ca.clinia.search.model.response.ResponseSearches
import ca.clinia.search.transport.RequestOptions

public interface EndpointMultipleIndex {

    /**
     * Perform a search on several indices at the same time, with one method call.
     * The returned results are broken down by [IndexQuery].
     * This method can be used in several different kinds of situations. Here are two typical usage scenarios:
     *
     * 1. You have multiple indices that serve different purposes. This is typical when you want to search your main
     * index as well as an index that contains a history of searches (to be used for autocomplete).
     *
     * 2. You want to target one index and send it multiple queries, where, for example, each query contains different
     * settings or filters, or the query itself is slightly adjusted.
     *
     * Note that for 2., you will want to use the [MultipleQueriesStrategy.StopIfEnoughMatches] value of the [strategy]
     * parameter.
     *
     * @param queries The [IndexQuery] that will execute each [Query] against its [IndexName]
     * @param strategy The [MultipleQueriesStrategy] of the query.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    suspend fun multipleQueries(
        queries: List<IndexQuery>,
        requestOptions: RequestOptions? = null
    ): ResponseSearches
}