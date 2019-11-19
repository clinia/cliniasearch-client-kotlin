package model.task

import ca.clinia.search.model.task.TaskStatus.NotPublished
import ca.clinia.search.model.task.TaskStatus.Published
import ca.clinia.search.serialize.KeyNotPublished
import ca.clinia.search.serialize.KeyPublished
import shouldEqual
import kotlin.test.Test

internal class TestTaskStatus {

    @Test
    fun raw() {
        Published.raw shouldEqual KeyPublished
        NotPublished.raw shouldEqual KeyNotPublished
    }
}