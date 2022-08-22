package net.spooncast.ext.collections

inline fun <T> List<T>.findWithIndexOrNull(predicate: (T) -> Boolean): Pair<Int, T>? {
    val index = indexOfFirst(predicate)
    return when (index < 0) {
        true -> null
        else -> index to get(index)
    }
}

inline fun <T> List<T>.replace(
    replaceItem: T,
    predicate: (T) -> Boolean,
): List<T> = map { item ->
    when (predicate(item)) {
        true -> replaceItem
        else -> item
    }
}

inline fun <T> Collection<T>.has(predicate: (T) -> Boolean): Boolean {
    return firstOrNull(predicate) != null
}
