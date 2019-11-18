package ca.clinia.search.model.task

import ca.clinia.search.model.IndexName
import ca.clinia.search.serialize.KeyIndexName
import ca.clinia.search.serialize.KeyTaskID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class TaskIndex(
    /**
     * The [IndexName] this task is running on.
     */
    @SerialName(KeyIndexName) val indexName: IndexName,
    /**
     * The [TaskID] which can be used with the [EndpointAdvanced.waitTask] method.
     */
    @SerialName(KeyTaskID) override val taskID: TaskID
) : Task