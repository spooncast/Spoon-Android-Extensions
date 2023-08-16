package net.spooncast.ext.string

import android.text.InputFilter
import android.text.Spanned

class StringInputFilter(
    private val regex: Regex
): InputFilter {
    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence {
        val filteredString = StringBuilder()
        for (i in start until end) {
            val char = source.getOrNull(i) ?: return ""
            if (char.toString().matches(regex)) {
                filteredString.append(char)
            }
        }
        return filteredString
    }
}