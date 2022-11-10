package net.spooncast.ext.common

import androidx.annotation.StringRes

sealed interface ToastContainer {

    data class Text(
        val text: String
    ): ToastContainer

    data class Resource(
        @StringRes val res: Int
    ): ToastContainer

    data class ResourceWithArgs(
        @StringRes val res: Int,
        val args: Array<Any>
    ): ToastContainer {
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
}
