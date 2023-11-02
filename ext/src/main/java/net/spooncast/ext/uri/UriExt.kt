package net.spooncast.ext.uri

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.webkit.URLUtil
import androidx.annotation.RequiresApi
import java.net.URL


@RequiresApi(Build.VERSION_CODES.P)
fun Uri.toBitmap(contentResolver: ContentResolver): Bitmap? {
    return runCatching {
        if (URLUtil.isNetworkUrl(this.toString())) {
            val url = URL(this.toString())
            BitmapFactory.decodeStream(url.openConnection().getInputStream())
        } else {
            ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, this))
        }
    }.getOrNull()
}