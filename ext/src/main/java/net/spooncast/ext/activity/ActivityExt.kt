package net.spooncast.ext.activity

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

val Activity?.isFinishingOrDestroyed: Boolean
    get() = this == null || this.isFinishing || this.isDestroyed

private val Activity?.inputMethodManager: InputMethodManager?
    get() = this?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager

fun Activity?.hideKeyboard(view: View?) {
    if (this == null) {
        return
    }

    if (view == null) {
        currentFocus?.let { inputMethodManager?.hideSoftInputFromWindow(it.windowToken, 0) }
    } else {
        inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Activity?.showSoftKeyboard(editText: EditText, newCursorPosition: Int? = null, delay: Long = 0L) {
    val cursorPos = when (newCursorPosition == null || newCursorPosition < 0) {
        true -> editText.text.toString().length
        else -> newCursorPosition
    }
    editText.setSelection(cursorPos, cursorPos)
    editText.requestFocus()

    if (delay > 0) {
        editText.postDelayed({
            inputMethodManager?.showSoftInput(editText, 0)
        }, delay)
    } else {
        inputMethodManager?.showSoftInput(editText, 0)
    }
    editText.setSelection(cursorPos, cursorPos)
    editText.requestFocus()
    inputMethodManager?.showSoftInput(editText, 0)
}