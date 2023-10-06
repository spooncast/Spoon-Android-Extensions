package net.spooncast.ext.uri

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.P)
fun Uri.toBitmap(contentResolver: ContentResolver): Bitmap? {
    return runCatching {
        ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, this))
    }.getOrNull()
}