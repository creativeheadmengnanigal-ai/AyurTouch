package org.ayurtouch.project.doctor.screens.settingScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ayurtouch.composeapp.generated.resources.Res
import ayurtouch.composeapp.generated.resources.doctor_dp
import ayurtouch.composeapp.generated.resources.down_arrow_ic
import ayurtouch.composeapp.generated.resources.lock_ic
import ayurtouch.composeapp.generated.resources.logout_ic
import ayurtouch.composeapp.generated.resources.top_arrow_ic
import org.ayurtouch.project.AppColors
import org.ayurtouch.project.DoctorNameTextStyle
import org.ayurtouch.project.DoctorSettingScreenString
import org.ayurtouch.project.ExperienceTextStyle
import org.ayurtouch.project.SettingTitleTextStyle
import org.ayurtouch.project.SettingToggleTextStyle
import org.ayurtouch.project.SettingValueTextStyle
import org.ayurtouch.project.doctor.utils.CustomTopAppBar
import org.ayurtouch.project.doctor.utils.CustomTopAppBarTitle
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview()
fun MenuSettingUIPreview() {
    val navController = rememberNavController()
    MenuSettingUI(navController)
}

@Composable
fun MenuSettingUI(navController: NavController) {
    Scaffold(
        topBar = { CustomTopAppBar("") },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(AppColors.White)
                .padding(innerPadding)
        ) {
            CustomTopAppBarTitle(
                title = "Settings",
                onArrowClick = {
                    navController.navigate("doctor_main")
                    Log.d("TAG", "Appointments arrow clicked.")
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {

                    SettingItemBox {
                        CustomSettingToggle("Set Pin", Res.drawable.lock_ic)
                    }

                    SettingItemBox {
                        CustomSettingToggle("Turn Off Notification") { isOn ->
                            Log.d("Toggle", "Notification state: $isOn")
                        }
                    }

                    SettingItemBox {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(AppColors.Green)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            CustomSettingToggle("Active", Res.drawable.down_arrow_ic)
                        }
                    }

                    SettingItemBox {
                        CustomSettingToggle("Logout", Res.drawable.logout_ic)
                    }
                }
            }
        }
    }
}
@Composable
fun SettingItemBox(content: @Composable () -> Unit) {
    // Outer box - CreamyWhite background, shadow, rounded corners
    Box(
        modifier = Modifier
            .fillMaxWidth()

            .clip(RoundedCornerShape(10.dp))
            .background(AppColors.CreamyWhite)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 12.dp)
                    .background(AppColors.White)
                ,
                contentAlignment = Alignment.CenterStart
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                         ,
                    contentAlignment = Alignment.CenterStart
                ) {
                    content()
                }

            }

            // Optional bottom colored stripe
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(AppColors.CreamyPeach)
            )
        }
    }
}
