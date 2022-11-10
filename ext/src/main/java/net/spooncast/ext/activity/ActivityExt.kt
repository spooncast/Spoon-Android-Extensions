package net.spooncast.ext.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.webkit.URLUtil

fun Activity?.startWebBrowser(url: String) {
    if (this == null) {
        return
    }

    val urlText = if (URLUtil.isNetworkUrl(url)) url else "https://$url"
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlText)))
}