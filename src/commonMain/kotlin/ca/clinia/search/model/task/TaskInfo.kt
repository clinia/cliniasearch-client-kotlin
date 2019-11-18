package ca.clinia.search.model.task

import ca.clinia.search.serialize.KeyPendingTask
import ca.clinia.search.serialize.KeyStatus
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class TaskInfo(
    /**
     * The [Task] current [TaskStatus].
     */
    @SerialName(KeyStatus) val status: TaskStatus,
    /**
     * Whether the index has remaining [Task]s running
     */
    @SerialName(KeyPendingTask) val pendingTask: Boolean
)