package ca.clinia.search.model.task

import ca.clinia.search.model.Raw
import ca.clinia.search.serialize.KeyNotPublished
import ca.clinia.search.serialize.KeyPublished
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


/**
 * Current status of a [Task].
 */
@Serializable(TaskStatus.Companion::class)
public sealed class TaskStatus(override val raw: String) : Raw<String> {

    /**
     * The [Task] has been processed by the server.
     */
    public object Published : TaskStatus(KeyPublished)

    /**
     * The [Task] has not yet been processed by the server.
     */
    public object NotPublished : TaskStatus(KeyNotPublished)

    public data class Other(override val raw: String) : TaskStatus(raw)

    companion object : KSerializer<TaskStatus> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: TaskStatus) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): TaskStatus {
            return when (val string = serializer.deserialize(decoder)) {
                KeyPublished -> Published
                KeyNotPublished -> NotPublished
                else -> Other(string)
            }
        }
    }
}