package ca.clinia.search.model.task

import ca.clinia.search.model.Raw
import ca.clinia.search.helper.toTaskID
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.LongSerializer


/**
 * This is a numeric value (up to 64bits) used to identify a [Task].
 * It can be used to perform a [EndpointAdvanced.waitTask] operation.
 */
@Serializable(TaskID.Companion::class)
public data class TaskID(override val raw: Long) : Raw<Long> {

    override fun toString(): String {
        return raw.toString()
    }

    companion object : KSerializer<TaskID> {

        private val serializer = LongSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: TaskID) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): TaskID {
            val long = serializer.deserialize(decoder)

            return long.toTaskID()
        }
    }
}