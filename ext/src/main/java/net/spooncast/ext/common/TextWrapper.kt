package net.spooncast.ext.common

import android.content.Context
import androidx.annotation.StringRes
import net.spooncast.ext.context.toast

sealed interface TextWrapper {

    data class Text(
        val text: String
    ): TextWrapper

    data class Resource(
        @StringRes val res: Int
    ): TextWrapper

    data class ResourceWithArgs(
        @StringRes val res: Int,
        val args: Array<Any>
    ): TextWrapper {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as ResourceWithArgs

            if (res != other.res) return false
            if (!args.contentEquals(other.args)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = res
            result = 31 * result + args.contentHashCode()
            return result
        }
    }

    fun showToast(context: Context) {
        when (this) {
            is Resource -> context.toast(res)
            is ResourceWithArgs -> context.toast(context.getString(res, *args))
            is Text -> context.toast(text)
        }
    }
}
