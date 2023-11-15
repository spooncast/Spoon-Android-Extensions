package net.spooncast.ext.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> Flow<T>.throttleFirst(
    timeoutMillis: Long
) = flow {
    var emitTime = 0L
    collect { upStream ->
        val currentTime = System.currentTimeMillis()
        if (currentTime - emitTime > timeoutMillis) {
            emitTime = currentTime
            emit(upStream)
        }
    }
}