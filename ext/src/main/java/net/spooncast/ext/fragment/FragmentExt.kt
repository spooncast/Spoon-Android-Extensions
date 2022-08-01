package net.spooncast.ext.fragment

import android.content.Intent
import android.net.Uri
import android.os.VibrationEffect
import android.webkit.URLUtil
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import net.spooncast.ext.context.shareUrl
import net.spooncast.ext.context.toast
import net.spooncast.ext.context.vibrate

fun Fragment?.startWebBrowser(url: String) {
    val urlText = if (URLUtil.isNetworkUrl(url)) url else "https://$url"
    this?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlText)))
}

fun Fragment?.toast(
    @StringRes msg: Int,
    duration: Int = Toast.LENGTH_SHORT
) {
    this?.context?.toast(msg, duration)
}

fun Fragment?.toast(
    msg: String?,
    duration: Int = Toast.LENGTH_SHORT
) {
    this?.context?.toast(msg, duration)
}

fun Fragment.shareUrl(textWithUrl: String) {
    requireContext().shareUrl(textWithUrl)
}

fun Fragment.vibrate(duration: Long, effect: Int = VibrationEffect.DEFAULT_AMPLITUDE) {
    context?.vibrate(duration, effect)
}