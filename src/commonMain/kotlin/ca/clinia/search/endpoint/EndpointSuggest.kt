package ca.clinia.search.endpoint

import ca.clinia.search.model.response.ResponseQuerySuggestions
import ca.clinia.search.model.suggest.SuggestionQuery
import ca.clinia.search.transport.RequestOptions

interface EndpointSuggest {
    /**
     * Method used to get query suggestions.
     *
     * @param query The [SuggestionQuery] used to suggest.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    suspend fun suggest(query: SuggestionQuery = SuggestionQuery(), requestOptions: RequestOptions? = null): ResponseQuerySuggestions
}