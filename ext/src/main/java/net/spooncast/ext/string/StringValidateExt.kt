package net.spooncast.ext.string

import android.webkit.URLUtil
import java.util.regex.Pattern

fun String?.isValidFormat(pattern: Pattern): Boolean {
    return !this.isNullOrBlank() && pattern.matcher(this).matches()
}

fun String?.isValidFormat(regex: Regex): Boolean {
    return !this.isNullOrBlank() && this.matches(regex)
}

val ipAddressRegex = """^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$""".toRegex()
val webSocketUrlRegex = "^(ws|wss)://[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(:[1-9][0-9]{0,4})?(/\\S*)?$".toRegex()

val String?.isUrl: Boolean
    get() = when (this.isNullOrBlank()) {
        true -> false
        else -> URLUtil.isNetworkUrl(this)
    }