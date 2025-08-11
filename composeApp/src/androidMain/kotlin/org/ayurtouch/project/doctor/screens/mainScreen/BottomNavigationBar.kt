
package org.ayurtouch.project.doctor.screens.mainScreen

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ayurtouch.composeapp.generated.resources.Res
import ayurtouch.composeapp.generated.resources.calendar_ic
import ayurtouch.composeapp.generated.resources.home_ic
import ayurtouch.composeapp.generated.resources.like_ic
import ayurtouch.composeapp.generated.resources.list_ic
import ayurtouch.composeapp.generated.resources.menu_ic
import org.ayurtouch.project.AppColors
import org.ayurtouch.project.NavigationLabelTextStyle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview()
@Composable
fun BottomNavigationBarPreview() {
    var selectedIndex by remember { mutableStateOf(0) }
    var isMenuExpanded by remember { mutableStateOf(false) }


    val sampleItems = listOf(
        BottomNavItem("Home", painterResource(Res.drawable.home_ic)),
        BottomNavItem("Leave", painterResource(Res.drawable.calendar_ic)),
        BottomNavItem("Patient", painterResource(Res.drawable.list_ic)),
        BottomNavItem("Media", painterResource(Res.drawable.like_ic)),
        BottomNavItem("Menu", painterResource(Res.drawable.menu_ic))
    )

    BottomNavigationBar(
        items = sampleItems,
        selectedIndex = selectedIndex,
        onItemSelected = { index ->
            selectedIndex = index
            if (index == 4) {
                isMenuExpanded = !isMenuExpanded

            } else {
                isMenuExpanded = false

            }
        },


        isMenuExpanded = isMenuExpanded,
        modifier = Modifier.fillMaxWidth()
    )
}



@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    isMenuExpanded: Boolean,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(horizontal = 30.dp, vertical = 30.dp)
            .clip(RoundedCornerShape(19.dp))
            .background(AppColors.CreamyPeach)
    ) {
        val itemWidth = maxWidth / items.size
        val indicatorOffset by animateDpAsState(targetValue = itemWidth * selectedIndex)

        Box(
            modifier = Modifier
                .offset(
                    x = indicatorOffset + (itemWidth - 36.dp) / 2,
                    y = (-12).dp
                )
                .size(width = 36.dp, height = 18.dp)
                .align(Alignment.TopStart)
                .clip(RoundedCornerShape(50))
                .background(Color(0xFFFD6F3E))
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                val isSelected =
                    index == selectedIndex || (index == 4 && isMenuExpanded)

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onItemSelected(index) },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = item.icon,
                        contentDescription = item.label,
                        tint = if (isSelected)
                            AppColors.OrangePrimary
                        else
                            AppColors.Brown,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    if (isSelected) {
                        Text(
                            text = item.label,
                            style = NavigationLabelTextStyle(),
                            color = AppColors.OrangePrimary
                        )
                    }
                }
            }
        }
    }
}
