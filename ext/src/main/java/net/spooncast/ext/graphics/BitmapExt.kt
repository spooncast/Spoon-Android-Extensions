package net.spooncast.ext.graphics

import android.graphics.Bitmap
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream

fun Bitmap.toInputStream(
    format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
    quality: Int = 90,
): InputStream {
    val bArrOutput = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 90, bArrOutput)
    return ByteArrayInputStream(bArrOutput.toByteArray())
}