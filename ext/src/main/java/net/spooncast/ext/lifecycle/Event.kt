package net.spooncast.ext.lifecycle

class Event<out T>(private val content: T) {

    private var hasBeenHandled = false

    fun getIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
}

fun emptyEvent(): Event<Unit> = Event(Unit)
