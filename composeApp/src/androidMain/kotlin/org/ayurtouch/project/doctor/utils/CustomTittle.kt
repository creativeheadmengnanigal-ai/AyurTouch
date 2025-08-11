package org.ayurtouch.project.doctor.utils


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import org.ayurtouch.project.AppColors
import org.ayurtouch.project.Monsetserrat600


@Composable
fun CustomTittle(
    tittle: String,
){
    Text(
        text = tittle,
        fontSize = 14.sp,
        fontFamily = Monsetserrat600(),
        color = AppColors.Brown
    )
}

@Composable
fun CustomTextView(
    tittle: String,
    fontSize: Int,
    font: FontFamily,
    color: Color,

    ) {
    Text(
        text = tittle,
        color = color,
        fontSize = fontSize.sp,
        fontFamily = font,
        modifier = Modifier
    )
}
@Composable
fun UnderlinedText(
    text: String,
    fontSize: Int,
    font: FontFamily,
    color: Color
) {
    Text(
        text = text,
        color = color,
        fontSize = fontSize.sp,
        fontFamily = font,
        textDecoration = TextDecoration.Underline,
        modifier = Modifier
    )
}
