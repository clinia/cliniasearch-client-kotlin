package ca.clinia.search.model.task

/**
 * A server-side task, used by [EndpointAdvanced.waitTask].
 */
public interface Task {

    val taskID: TaskID
}