package ca.clinia.search.model.response

import kotlinx.serialization.Serializable

@Serializable
public data class ResponseRecordWithPosition(
    val record: ResponseSearch.Record,
    val position: Int,
    val page: Int
)