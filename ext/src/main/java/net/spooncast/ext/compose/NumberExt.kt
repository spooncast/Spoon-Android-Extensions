package net.spooncast.ext.compose

import android.content.res.Resources

val Number.dp2px: Float
    get() = this.toFloat() * (Resources.getSystem().displayMetrics.density)


val Number.px2dp: Float
    get() = this.toFloat() / (Resources.getSystem().displayMetrics.density)
