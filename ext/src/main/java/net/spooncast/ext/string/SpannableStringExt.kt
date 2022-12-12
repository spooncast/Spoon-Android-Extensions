package net.spooncast.ext.string

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.View

private const val URL_PATTERN = "(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})"
private val urlRegex: Regex = URL_PATTERN.toRegex()

fun String.hyperlinkText(
    urlColor: Int,
    onClickUrl: (url: String) -> Unit
): SpannableString {
    val urlMatchResult = urlRegex.findAll(this)
    val spannableString = SpannableString(this)
    if (urlMatchResult.count() > 0) {
        applyUrlText(spannableString, urlMatchResult, urlColor, onClickUrl)
    }

    return spannableString
}

private fun applyUrlText(
    spannableString: SpannableString,
    matchResult: Sequence<MatchResult>,
    color: Int,
    onClick: (url: String) -> Unit
) {
    matchResult.forEach {
        val startPos = it.range.first
        val endPos = it.range.last + 1
        val tosClick = object : ClickableSpan() {
            override fun onClick(v: View) {
                onClick(it.value)
            }
        }
        spannableString.setSpan(tosClick, startPos, endPos, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(
            ForegroundColorSpan(color),
            startPos,
            endPos,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            UnderlineSpan(),
            startPos,
            endPos,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}
