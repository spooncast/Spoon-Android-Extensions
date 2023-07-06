package net.spooncast.ext.string

fun String.substringByLength(length: Int): String {
    return this.substring(0 until length.coerceAtMost(this.length))
}

val CharSequence?.removeNewLine : CharSequence
    get() = this?.replace("(\r\n|\r|\n|\n\r)".toRegex(), "") ?: ""
