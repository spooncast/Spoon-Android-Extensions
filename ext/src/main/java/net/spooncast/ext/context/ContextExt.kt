package net.spooncast.ext.context

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.app.ShareCompat
import java.util.Locale
import java.util.TimeZone
import net.spooncast.ext.context.country.getCountryCodeByTimeZone


fun Context?.toast(
    @StringRes msg: Int,
    duration: Int = Toast.LENGTH_SHORT,
) {
    toast(this?.getString(msg), duration)
}

fun Context?.toast(
    msg: String?,
    duration: Int = Toast.LENGTH_SHORT,
) {
    Toast.makeText(this, msg, duration).show()
}

fun Context.showNotificationSettings() {
    val intent = Intent("android.settings.APP_NOTIFICATION_SETTINGS")
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    if (android.os.Build.VERSION.SDK_INT < 26) {
        intent.putExtra("app_package", packageName)
            .putExtra("app_uid", applicationInfo.uid)
    } else {
        intent.putExtra("android.provider.extra.APP_PACKAGE", packageName)
    }
    startActivity(intent)
}

fun Context.shareUrl(urlText: String) {
    ShareCompat.IntentBuilder(this)
        .setType("text/plain")
        .setText(urlText)
        .startChooser()
}

fun Context.vibrate(duration: Long, effect: Int = VibrationEffect.DEFAULT_AMPLITUDE) {
    val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vibrator?.vibrate(VibrationEffect.createOneShot(duration, effect))
    } else {
        vibrator?.vibrate(duration)
    }
}

fun Context.isDarkTheme(): Boolean {
    return resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
}

fun Context.isRTL(): Boolean {
    return resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_RTL
}

/**
 * @return 2 letter country code (lowercase)
 */
fun Context.getCountryCode(): String {
    val localeBasedCode = Locale.getDefault().country
    val defaultCountryCode = getCountryCodeByTimeZone(TimeZone.getDefault().id) ?: localeBasedCode
    val telMgr = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager? ?: return defaultCountryCode
    return when (telMgr.simState) {
        TelephonyManager.SIM_STATE_READY -> telMgr.networkCountryIso.ifBlank { defaultCountryCode }
        else -> defaultCountryCode
    }.lowercase()
}