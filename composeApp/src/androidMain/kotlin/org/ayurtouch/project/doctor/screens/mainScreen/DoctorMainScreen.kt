package org.ayurtouch.project.doctor.screens.mainScreen

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.*
import ayurtouch.composeapp.generated.resources.*
import org.ayurtouch.project.AppColors
import org.ayurtouch.project.DoctorMainScreenString
import org.ayurtouch.project.MenuLabelTextStyle
import org.ayurtouch.project.doctor.navigationDoctorFlow.Screen
import org.ayurtouch.project.doctor.screens.homeScreen.HomeScreen
import org.ayurtouch.project.doctor.screens.settingScreen.SettingsViewModel
import org.ayurtouch.project.doctor.utils.CustomTopAppBar
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@RequiresApi(Build.VERSION_CODES.S)
@Preview
@Composable
fun DoctorMainScreenPreview() {
    val navController = rememberNavController()
    DoctorMainScreen(navController)
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun DoctorMainScreen(navController: NavController) {
    var selectedIndex by remember { mutableStateOf(0) }
    var isMenuExpanded by remember { mutableStateOf(false) }
    var isChatOverlayVisible by remember { mutableStateOf(true) }

    val items = listOf(
        BottomNavItem(DoctorMainScreenString.HOME, painterResource(Res.drawable.home_ic)),
        BottomNavItem(DoctorMainScreenString.LEAVE, painterResource(Res.drawable.calendar_ic)),
        BottomNavItem(DoctorMainScreenString.PATIENT, painterResource(Res.drawable.list_ic)),
        BottomNavItem(DoctorMainScreenString.MEDIA, painterResource(Res.drawable.like_ic)),
        BottomNavItem(DoctorMainScreenString.MENU, painterResource(Res.drawable.menu_ic))
    )


    Scaffold(
        topBar = {
            CustomTopAppBar("")
        }, containerColor = Color.Transparent
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Show HomeScreen if index == 4 so it’s always behind the menu overlay
            when (selectedIndex) {
                0 -> HomeScreen(navController)
                1 -> HomeScreen(navController) // replace with LeaveScreen
                2 -> HomeScreen(navController) // replace with PatientScreen
                3 -> HomeScreen(navController) // replace with MediaScreen
                4 -> HomeScreen(navController)
            }


            // Bottom Navigation Bar
            BottomNavigationBar(
                items = items,
                selectedIndex = selectedIndex,
                onItemSelected = { index ->
                    selectedIndex = index
                    if (index == 4) {
                        isMenuExpanded = !isMenuExpanded
                        isChatOverlayVisible = false
                    } else {
                        isMenuExpanded = false
                        isChatOverlayVisible = (index == 0) // show overlay only for Home
                    }
                },
                isMenuExpanded = isMenuExpanded,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
            // Chat clicked
            if (isChatOverlayVisible) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = (-30).dp, y = (-100).dp)
                        .size(40.dp)
                        .clip(RoundedCornerShape(50))
                        .background(AppColors.Brown)
                        .clickable {
                            isChatOverlayVisible = false
                            navController.navigate("chat_screen")
                        }, contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.chat_ic),
                        contentDescription = "Chat",
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            if (isMenuExpanded) {
                Box(
                    modifier = Modifier
                        .offset(y = (-100).dp)
                        .size(width = 200.dp, height = 190.dp)
                        .align(Alignment.BottomEnd)
                        .padding(end = 30.dp)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    // Background blur
                    Box(modifier = Modifier
                        .matchParentSize()
                        .graphicsLayer {
                            renderEffect = RenderEffect.createBlurEffect(
                                30f, 30f, Shader.TileMode.CLAMP
                            ).asComposeRenderEffect()
                        }
                        .background(Color(0x66FD6F3E)))

                    // White foreground card
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .padding(3.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(AppColors.White)
                            .padding(16.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier.fillMaxSize()

                        ) {
                            val settingsViewModel: SettingsViewModel = viewModel()
                            MenuItemRow(
                                iconRes = Res.drawable.setting_ic,
                                label = DoctorMainScreenString.SETTINGS
                            ) {

                                isMenuExpanded = false
                                selectedIndex = 0
                                settingsViewModel.setMenuSetting(false)
                                navController.navigate(
                                    Screen.DoctorSetting.route + "?isSetting=false" + "&isMenuSetting=true"

                                )
                            }
                            MenuItemRow(
                                iconRes = Res.drawable.chat_ic, label = DoctorMainScreenString.CHAT
                            ) {
                                isMenuExpanded = false
                                selectedIndex = 0
                                navController.navigate("chat_screen")
                            }
                            MenuItemRow(
                                iconRes = Res.drawable.announcement_ic,
                                label = DoctorMainScreenString.ANNOUNCEMENT
                            ) {
                                isMenuExpanded = false
                                selectedIndex = 0
                                navController.navigate(Screen.DoctorAnnouncement.route)
                            }
                            MenuItemRow(
                                iconRes = Res.drawable.logout_ic,
                                label = DoctorMainScreenString.LOGOUT
                            ) {
                                isMenuExpanded = false
                                selectedIndex = 0
                                navController.navigate("logout_screen")
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun MenuItemRow(
    iconRes: DrawableResource, label: String, onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp)) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = label,
            tint = Color.Black,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label, style = MenuLabelTextStyle()
        )
    }
}
