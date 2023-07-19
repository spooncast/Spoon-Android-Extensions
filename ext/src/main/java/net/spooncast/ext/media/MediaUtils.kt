package net.spooncast.ext.media

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment

fun Activity.selectFile(
    contentType: String,
    guideText: String,
    reqCode: Int,
) {
    startActivityForResult(getChooserIntent(contentType, guideText), reqCode)
}

fun Fragment.selectFile(
    contentType: String,
    guideText: String,
    reqCode: Int,
) {
    startActivityForResult(getChooserIntent(contentType, guideText), reqCode)
}

private fun getChooserIntent(contentType: String, guideText: String): Intent {
    val target = Intent(Intent.ACTION_PICK).apply { type = contentType }
    return Intent.createChooser(target, guideText)
}

const val REQ_SELECT_FILE_IMAGE = 202307191221
const val REQ_SELECT_FILE_AUDIO = 202307191222