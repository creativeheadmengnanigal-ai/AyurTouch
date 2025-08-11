package org.ayurtouch.project.doctor.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ayurtouch.project.Monsetserrat500
import org.jetbrains.compose.resources.DrawableResource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import org.jetbrains.compose.resources.painterResource

import androidx.compose.material.Button

import androidx.compose.material.Icon
import androidx.compose.material.Text


@Composable
fun CustomButton(
    buttonText: String,
    buttonTextColor: Color,
    buttonTextSize: Int,
    buttonRadius: Int,
    buttonColor: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = buttonColor
        ),
        shape = RoundedCornerShape(buttonRadius.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        CustomTextView(
            tittle = buttonText,
            fontSize = buttonTextSize,
            color = buttonTextColor,
            font = Monsetserrat500()
        )
    }
}




@Composable
fun CustomButtonIconAndText(
    buttonHeight:Int,
    buttonWidth:Int,
    buttonText: String,
    buttonTextColor: Color,
    buttonTextSize: Int,
    buttonRadius: Int,
    buttonColor: Color,
    buttonBorderColor: Color,
    buttonIcon: DrawableResource,
    buttonIconSize: Int,
    buttonIconColor: Color,
    onClick: () -> Unit
) {
    val iconPainter = painterResource(buttonIcon)

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = buttonColor
        ),
        shape = RoundedCornerShape(buttonRadius.dp),
        border = BorderStroke(1.dp, buttonBorderColor),
        modifier = Modifier
            .width(buttonWidth.dp)
            .height(buttonHeight.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = buttonText,
                color = buttonTextColor,
                fontSize = buttonTextSize.sp,
                fontFamily = Monsetserrat500()
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = iconPainter,
                contentDescription = null,
                tint = buttonIconColor,
                modifier = Modifier
                    .size(buttonIconSize.dp)
            )


        }
    }
}

