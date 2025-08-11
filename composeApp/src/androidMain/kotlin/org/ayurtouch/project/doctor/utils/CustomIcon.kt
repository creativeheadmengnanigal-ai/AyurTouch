package org.ayurtouch.project.doctor.utils

import android.R.attr.onClick
import android.icu.lang.UCharacter
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.painterResource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource


@Composable
fun CustomIcon(
    onClick: () -> Unit,
    imageRes: Any,
    imageSize:Int,
    isClick: Boolean = false,

) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .clickable(
                enabled = isClick,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(imageRes as DrawableResource),
            contentDescription = "Clickable Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(imageSize.dp)
        )
    }
}

@Composable
fun CustomImage(
    onClick: () -> Unit,
    imageRes: Any,
    isClick: Boolean = false,

    ) {
    Box(
        modifier = Modifier
            .clickable(
                enabled = isClick,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(imageRes as DrawableResource),
            contentDescription = "Clickable Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

//User define size image
@Composable
fun CustomWrapImage(
    onClick: () -> Unit,
    imageRes: Any,
    height:Int,
    width: Int,
    isClick: Boolean = false,

    ) {
    Box(
        modifier = Modifier.height(height.dp).width(width.dp)
            .clickable(
                enabled = isClick,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.height(height.dp).width(width.dp) ,
            painter = painterResource(imageRes as DrawableResource),
            contentDescription = "Clickable Image",

        )
    }
}