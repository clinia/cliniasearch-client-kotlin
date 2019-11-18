package ca.clinia.search.endpoint

import ca.clinia.search.model.IndexName
import ca.clinia.search.model.response.ResponseSearch
import ca.clinia.search.model.search.Query
import ca.clinia.search.transport.RequestOptions

public interface EndpointSearch {

    val indexName: IndexName

    /**
     * Method used for querying an index.
     * The search query only allows for the retrieval of up to 1000 hits.
     * If you need to retrieve more than 1000 hits (e.g. for SEO), you can either leverage
     * the [Browse index][https://www.algolia.com/doc/api-reference/api-methods/browse/] method or increase
     * the [paginationLimitedTo][https://www.algolia.com/doc/api-reference/api-parameters/paginationLimitedTo/] parameter.
     *
     * @param query The [Query] used to search.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    suspend fun search(query: Query = Query(), requestOptions: RequestOptions? = null): ResponseSearch

}