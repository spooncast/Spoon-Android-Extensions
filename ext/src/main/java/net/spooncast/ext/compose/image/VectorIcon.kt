package net.spooncast.ext.compose.image

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VectorIcon(
    @DrawableRes resId: Int,
    size: Dp = 28.dp,
    tint: Color,
    contentDesc: String = "",
    onClick: () -> Unit
) {
    Icon(
        imageVector = ImageVector.vectorResource(id = resId),
        contentDescription = contentDesc,
        modifier = Modifier
            .size(size)
            .clickable(onClick = onClick),
        tint = tint
    )
}
