package net.spooncast.ext.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun HorizontalSpacer(
    size: Dp,
    color: Color = Color.Transparent
) {
    Spacer(
        modifier = Modifier
            .background(color)
            .width(size)
    )
}

@Composable
fun VerticalSpacer(
    size: Dp,
    color: Color = Color.Transparent
) {
    Spacer(
        modifier = Modifier
            .background(color)
            .height(size)
    )
}