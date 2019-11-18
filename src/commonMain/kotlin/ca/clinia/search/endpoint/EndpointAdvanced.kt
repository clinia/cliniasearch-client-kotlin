package ca.clinia.search.endpoint

import ca.clinia.search.model.IndexName
import ca.clinia.search.model.task.Task
import ca.clinia.search.model.task.TaskID
import ca.clinia.search.model.task.TaskInfo
import ca.clinia.search.model.task.TaskStatus
import ca.clinia.search.transport.RequestOptions

public interface EndpointAdvanced {

    /**
     * The [IndexName] used by [Index] to perform operations on.
     */
    val indexName: IndexName

    /**
     * Check the current [TaskStatus] of a given [Task].
     *
     * @param taskID of the indexing [Task].
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    suspend fun getTask(
        taskID: TaskID,
        requestOptions: RequestOptions? = null
    ): TaskInfo

    /**
     * Wait for a [Task] to complete before executing the next line of code, to synchronize index updates.
     * All write operations in Algolia are asynchronous by design.
     * It means that when you add or update an object to your index, our servers will reply to your request with
     * a [taskID] as soon as they understood the write operation.
     * The actual insert and indexing will be done after replying to your code.
     * You can wait for a task to complete by using the [taskID] and this method.
     *
     * @param taskID of the indexing task to wait for.
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    suspend fun waitTask(
        taskID: TaskID,
        timeout: Long? = null,
        requestOptions: RequestOptions? = null
    ): TaskStatus

    /**
     * @see waitTask
     *
     * @param timeout If specified, the method will throw a [kotlinx.coroutines.TimeoutCancellationException] after the
     * timeout value in milliseconds is elapsed.
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    suspend fun Task.wait(
        timeout: Long? = null,
        requestOptions: RequestOptions? = null
    ): TaskStatus

    /**
     * @see wait
     *
     * Wait for several [Task] in parallel to complete.
     *
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    suspend fun List<Task>.wait(
        timeout: Long? = null,
        requestOptions: RequestOptions? = null
    ): List<TaskStatus>
}