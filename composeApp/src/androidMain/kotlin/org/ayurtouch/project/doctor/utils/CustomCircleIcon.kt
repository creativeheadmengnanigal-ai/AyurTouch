package org.ayurtouch.project.doctor.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomCircleIcon(
    size: Dp,
    backgroundColor: Color,
    icon: Painter,
    shadowColor: Color = Color.Gray,
    onClick: () -> Unit = {},
    iconTintColor: Color,
    iconSize: Dp
) {
    Box(
        modifier = Modifier
            .size(size)
            .shadow(
                elevation = 8.dp,
                shape = CircleShape,
                ambientColor = shadowColor,
                spotColor = shadowColor
            )

            .background(backgroundColor)
            .clip(CircleShape)
            .clickable { onClick() } ,
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = icon,
            contentDescription = "Circle Icon",
            tint = iconTintColor,
            modifier = Modifier.size( iconSize) // Icon size relative to circle
        )
    }
}

