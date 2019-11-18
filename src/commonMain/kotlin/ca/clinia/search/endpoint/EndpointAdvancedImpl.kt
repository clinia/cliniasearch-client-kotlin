package ca.clinia.search.endpoint

import ca.clinia.search.configuration.CallType
import ca.clinia.search.dsl.requestOptionsBuilder
import ca.clinia.search.model.IndexName
import ca.clinia.search.model.task.Task
import ca.clinia.search.model.task.TaskID
import ca.clinia.search.model.task.TaskInfo
import ca.clinia.search.model.task.TaskStatus
import ca.clinia.search.serialize.*
import ca.clinia.search.transport.RequestOptions
import ca.clinia.search.transport.Transport
import io.ktor.http.HttpMethod
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout


internal class EndpointAdvancedImpl(
    private val transport: Transport,
    override val indexName: IndexName
) : EndpointAdvanced {

    override suspend fun List<Task>.wait(timeout: Long?, requestOptions: RequestOptions?): List<TaskStatus> {

        suspend fun loop(): List<TaskStatus> {
            return coroutineScope {
                map { async { it.wait(requestOptions = requestOptions) } }.map { it.await() }
            }
        }

        return timeout?.let { withTimeout(it) { loop() } } ?: loop()
    }

    override suspend fun Task.wait(timeout: Long?, requestOptions: RequestOptions?): TaskStatus {
        return waitTask(taskID, timeout, requestOptions)
    }

    override suspend fun waitTask(taskID: TaskID, timeout: Long?, requestOptions: RequestOptions?): TaskStatus {

        suspend fun loop(): TaskStatus {
            while (true) {
                getTask(taskID, requestOptions).status.let {
                    if (it == TaskStatus.Published) return it
                }
                delay(1000L)
            }
        }

        return timeout?.let { withTimeout(it) { loop() } } ?: loop()
    }

    override suspend fun getTask(taskID: TaskID, requestOptions: RequestOptions?): TaskInfo {
        return transport.request(HttpMethod.Get, CallType.Read, indexName.toPath("/task/$taskID"), requestOptions)
    }
}