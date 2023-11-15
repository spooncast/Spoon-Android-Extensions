package net.spooncast.ext.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> Flow<T>.throttleFirst(
    timeoutMillis: Long,
    currentTimeMillis: Long = System.currentTimeMillis()
) = flow {
    var lastEmitTimeMillis = 0L
    collect {
        if (currentTimeMillis - lastEmitTimeMillis > timeoutMillis) {
            lastEmitTimeMillis = currentTimeMillis
            emit(it)
        }
    }
}