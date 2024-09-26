package net.spooncast.ext.graphics

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.webkit.URLUtil
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.URL

fun Bitmap.toInputStream(
    format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
    quality: Int = 90,
    maxResolution: Float? = null
): InputStream {
    val bArrOutput = ByteArrayOutputStream()
    val bitmap = scaledBitmap(maxResolution)
    bitmap.compress(format, quality, bArrOutput)
    return ByteArrayInputStream(bArrOutput.toByteArray())
}

private fun Bitmap.scaledBitmap(maxResolution: Float?): Bitmap {
    if (maxResolution == null) {
        return this
    }

    val ratio = if (width > maxResolution) {
        maxResolution / width
    } else if (height > maxResolution) {
        maxResolution / height
    } else {
        return this
    }

    val destWidth = (width * ratio).toInt()
    val destHeight = (height * ratio).toInt()

    return Bitmap.createScaledBitmap(this, destWidth, destHeight, true)
}

const val RESOLUTION_FHD = 1920F

fun loadBitmapByUrl(
    url: String?,
    bitmapOption: BitmapFactory.Options? = null
): Bitmap? {
    if (!URLUtil.isNetworkUrl(url)) {
        return null
    }

    try {
        val inputStream = URL(url)
            .openConnection()
            .apply { connectTimeout = 10_000 }
            .getInputStream()
        return BitmapFactory.decodeStream(inputStream, null, bitmapOption) ?: return null
    } catch (t: Throwable) {
        return null
    }
}