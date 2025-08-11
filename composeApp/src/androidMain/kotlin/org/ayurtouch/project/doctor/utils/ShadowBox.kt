package org.ayurtouch.project.doctor.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.ayurtouch.project.AppColors

//Shadow full width + used define height
@Composable
fun CustomHeightShadowBox(
    boxHeight: Int,
    horizontal: Int,
    vertical: Int,
    backgroundColor: Color,
    cornerRadius: Int,
    blurRadius: Int,
    content: @Composable () -> Unit
) {
    Box(
        modifier =  Modifier
            .height(boxHeight.dp)
            .fillMaxWidth()
            .padding(horizontal = horizontal.dp, vertical = vertical.dp),
    ) {

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(cornerRadius)
                )
                .blur(blurRadius.dp)
        )

        // Foreground content
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(2.dp)
                .clip(RoundedCornerShape(cornerRadius))
                .background(AppColors.White)
        ) {
            content()
        }
    }
}


//Shadow full width + wrap height
@Composable
fun CustomWrapHeightShadowBox(
    horizontal: Int,
    vertical: Int,
    backgroundColor: Color,
    cornerRadius: Int,
    blurRadius: Int,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontal.dp, vertical = vertical.dp),
        contentAlignment = Alignment.Center
    ) {
        // The blurred shadow background
        Box(
            modifier = Modifier
                .matchParentSize()

                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(cornerRadius)
                )
                .blur(blurRadius.dp)
        )

        // Foreground content box
        Box(
            modifier = Modifier
                .padding(2.dp)
                .clip(RoundedCornerShape(cornerRadius))
                .background(AppColors.White)
                // or whatever inner padding you want
        ) {
            content()
        }
    }
}



//Shadow wrap height & width
@Composable
fun CustomWrapShadowBox(
    boxHeight: Int,
    boxWidth: Int,
    horizontal: Int,
    vertical: Int,
    backgroundColor: Color,
    cornerRadius: Int,
    blurRadius: Int,
    content: @Composable () -> Unit,

) {
    Box(
        modifier =  Modifier
            .height(boxHeight.dp)
            .width(boxWidth.dp)
            .padding(horizontal = horizontal.dp, vertical = vertical.dp),
    ) {

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(cornerRadius)
                )
                .blur(blurRadius.dp)
        )

        // Foreground content
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(2.dp)
                .clip(RoundedCornerShape(cornerRadius))
                .background(AppColors.White)
        ) {
            content()
        }
    }
}