package org.ayurtouch.project.doctor.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ayurtouch.composeapp.generated.resources.Res
import ayurtouch.composeapp.generated.resources.arrow_ic
import ayurtouch.composeapp.generated.resources.left_arrow_ic
import org.ayurtouch.project.AppColors
import org.ayurtouch.project.AppointmentsTextStyle
import org.ayurtouch.project.Monsetserrat500
import org.jetbrains.compose.resources.painterResource





@Composable
fun CustomTopAppBarTitle(
    title: String,
    onArrowClick: (() -> Unit)? = null,
    iconPainter: Painter? = null,
    iconSize: Dp = 24.dp,
    contentPadding: PaddingValues = PaddingValues(horizontal = 20.dp, vertical = 20.dp)
) {
    val arrowPainter = iconPainter ?: painterResource(Res.drawable.left_arrow_ic)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(contentPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            painter = arrowPainter,
            contentDescription = "Back arrow",
            modifier = Modifier
                .size(iconSize)
                .clickable(enabled = onArrowClick != null) {
                    onArrowClick?.invoke()
                },
            tint = AppColors.VibrantOrange
        )
        Box(modifier = Modifier.fillMaxWidth()){
            Text(
                text = title,
                color = AppColors.VibrantOrange,
                fontFamily = Monsetserrat500(),
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }

    }
}
