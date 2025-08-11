
package org.ayurtouch.project.doctor.screens.settingScreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.ayurtouch.project.doctor.utils.CustomTopAppBar
import org.jetbrains.compose.ui.tooling.preview.Preview
@Preview
@Composable
fun SettingsScreenPreview() {
    val navController = rememberNavController()
    SettingsScreen(navController = navController )

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    navController: NavController,
    isMenuSetting: Boolean = false,
    isSetting: Boolean = false,
) {


    Scaffold(
        topBar = { CustomTopAppBar("") }
    ) {
        when {
            isMenuSetting -> {
                MenuSettingUI(navController)
            }

            isSetting -> {
                SettingUI(navController)
            }

        }
    }


}

