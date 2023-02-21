package net.spooncast.ext.compose.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale

@Composable
fun ZoomableImage(
    painter: Painter,
    scaleDefault: Float = SCALE_DEFAULT,
    scaleMax: Float = SCALE_MAX
) {
    val scale = remember { mutableStateOf(scaleDefault) }
    val transX = remember { mutableStateOf(0f) }
    val transY = remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale.value *= zoom
                    if (scale.value > 1) {
                        transX.value += pan.x
                        transY.value += pan.y
                    } else {
                        transX.value = 0F
                        transY.value = 0F
                    }
                }
            }
    ) {
        val scaleValue = maxOf(scaleDefault, minOf(scaleMax, scale.value))
        Image(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .graphicsLayer(
                    scaleX = scaleValue,
                    scaleY = scaleValue,
                    translationX = transX.value,
                    translationY = transY.value
                ),
            contentDescription = null,
            painter = painter,
            contentScale = ContentScale.Fit
        )
    }
}

private const val SCALE_DEFAULT = 1F
private const val SCALE_MAX = 4F