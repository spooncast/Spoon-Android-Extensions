package net.spooncast.ext.string

fun String.substringByLength(length: Int): String {
    return this.substring(0 until length.coerceAtMost(this.length))
}
