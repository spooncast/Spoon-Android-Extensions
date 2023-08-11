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
        return if (source.matches(regex)) {
            source
        } else {
            ""
        }
    }
}