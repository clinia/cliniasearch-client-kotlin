package ca.clinia.search.helper

import ca.clinia.search.model.*
import ca.clinia.search.model.search.Point
import ca.clinia.search.model.task.TaskID


/**
 * Convenience method to convert [this] to an [IndexName].
 */
public fun String.toIndexName(): IndexName {
    return IndexName(this)
}

/**
 * Convenience method to convert [this] to an [Attribute].
 */
public fun String.toAttribute(): Attribute {
    return Attribute(this)
}

/**
 * Convenience method to convert [this] to an [ID].
 */
public fun String.toRecordID(): ID {
    return ID(this)
}

/**
 * Convenience method to convert [this] to a [TaskID].
 */
public fun Long.toTaskID(): TaskID {
    return TaskID(this)
}

/**
 * Convenience method to convert [this] to an [ApplicationID].
 */
public fun String.toApplicationID(): ApplicationID {
    return ApplicationID(this)
}

/**
 * Convenience method to convert [this] to an [APIKey].
 */
public fun String.toAPIKey(): APIKey {
    return APIKey(this)
}

/**
 * Convenience method to create a [Point] with [this] as [Point.latitude] and [longitude] as [Point.longitude].
 */
public infix fun Float.and(longitude: Float): Point {
    return Point(this, longitude)
}