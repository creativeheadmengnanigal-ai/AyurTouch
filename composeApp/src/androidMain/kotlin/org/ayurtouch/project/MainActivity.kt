//package org.ayurtouch.project
//
//
//import android.annotation.SuppressLint
//import android.content.IntentFilter
//import org.ayurtouch.project.doctor.screens.mainScreen.DoctorMainScreen
//import android.os.Build
//import android.os.Bundle
//import android.view.WindowInsets
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.activity.viewModels
//import androidx.annotation.RequiresApi
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.runtime.*
//import androidx.core.view.WindowCompat
//import androidx.navigation.NavType
//import androidx.navigation.compose.*
//import androidx.navigation.navArgument
//import com.google.android.gms.auth.api.phone.SmsRetriever
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.FirebaseApp
//import org.ayurtouch.project.doctor.navigationDoctorFlow.Screen
//import org.ayurtouch.project.doctor.screens.annoncementScreen.AnnouncementScreen
//import org.ayurtouch.project.doctor.screens.appointmentScreen.DoctorAppointmentScreen
//import org.ayurtouch.project.doctor.screens.appointmentScreen.cancelation.DoctorAppointmentCancellationScreen
//import org.ayurtouch.project.doctor.screens.appointmentScreen.reSchedule.DoctorAppointmentRescheduleScreen
//import org.ayurtouch.project.doctor.screens.doctorChatScreen.ChatDetailScreen
//import org.ayurtouch.project.doctor.screens.doctorChatScreen.DoctorChatScreen
//import org.ayurtouch.project.doctor.screens.homeScreen.HomeScreen
//import org.ayurtouch.project.doctor.screens.loginScreen.view.DoctorLoginScreen
//import org.ayurtouch.project.doctor.screens.loginScreen.viewmodel.DoctorLoginViewModel
//import org.ayurtouch.project.doctor.screens.loginScreen.model.SmsBroadcastReceiver
//
//import org.ayurtouch.project.doctor.screens.notificationScreen.NotificationScreen
//import org.ayurtouch.project.doctor.screens.settingScreen.SettingsScreen
//import org.ayurtouch.project.doctor.screens.splashScreen.SplashScreen
//
//
//open class MainActivity : ComponentActivity() {
//    private lateinit var auth: FirebaseAuth
//
//    private lateinit var viewModel: DoctorLoginViewModel
//
//    private val loginViewModel: DoctorLoginViewModel by viewModels()
//
//    private lateinit var smsReceiver: SmsBroadcastReceiver
//
//    @SuppressLint("UnspecifiedRegisterReceiverFlag")
//    override fun onStart() {
//        super.onStart()
//        smsReceiver = SmsBroadcastReceiver().apply {
//            otpReceived = { code ->
//                loginViewModel.updateOtp(code)
//            }
//        }
//        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
//        registerReceiver(smsReceiver, intentFilter)
//    }
//
//    override fun onStop() {
//        super.onStop()
//        unregisterReceiver(smsReceiver)
//    }
//
//    @RequiresApi(Build.VERSION_CODES.R)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//
//        auth = FirebaseAuth.getInstance()
//        viewModel = DoctorLoginViewModel()
//
//
//
//
//        FirebaseApp.initializeApp(this)
//
//
//
//        enableEdgeToEdge()
//        WindowCompat.setDecorFitsSystemWindows(window, false)
//
//
//
//        setContent {
//
//            MaterialTheme {
//
//                val navController = rememberNavController()
//                val currentBackStackEntry by navController.currentBackStackEntryAsState()
//                val currentRoute = currentBackStackEntry?.destination?.route
//
//                LaunchedEffect(currentRoute) {
//                    val controller = window.insetsController
//                    if (currentRoute == Screen.Auth.route) {
//                        controller?.hide(WindowInsets.Type.statusBars())
//                    } else {
//                        controller?.show(WindowInsets.Type.statusBars())
//                    }
//                }
//
//                val TEST_MODE = false
//                NavHost(
//                    navController = navController,
//                    startDestination = if (TEST_MODE) Screen.DoctorMain.route else Screen.Splash.route
//                ) {
//                    composable("splash") {
//                        SplashScreen(navController)
//                    }
//
//                    composable("auth") {
//                        DoctorLoginScreen(
//                            activity = this@MainActivity,
//                            auth = auth,
//                            navController = navController,
//                            viewModel = viewModel
//                        )
//                    }
//                    composable(Screen.DoctorMain.route) {
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//                            DoctorMainScreen(navController)
//                        }
//                    }
//                    composable(Screen.DoctorHome.route) {
//                        HomeScreen(navController)
//                    }
//
//                    composable("notification_screen") {
//                        NotificationScreen(navController)
//                    }
//
//                    composable(
//                        route = Screen.DoctorAppointment.route +
//                                "?isAppointmentHome={isAppointmentHome}" +
//                                "&isCancelled={isCancelled}" +
//                                "&isReschedule={isReschedule}" +
//                                "&isStartConsulting={isStartConsulting}" +
//                                "&isPreview={isPreview}",
//                        arguments = listOf(
//                            navArgument("isAppointmentHome") { defaultValue = "false" },
//                            navArgument("isCancelled") { defaultValue = "false" },
//                            navArgument("isReschedule") { defaultValue = "false" },
//                            navArgument("isStartConsulting") { defaultValue = "false" },
//                            navArgument("isPreview") { defaultValue = "false" }
//                        )
//                    ) { backStackEntry ->
//
//                        val isAppointmentHome = backStackEntry.arguments
//                            ?.getString("isAppointmentHome")?.toBoolean() ?: false
//                        val isCancelled = backStackEntry.arguments
//                            ?.getString("isCancelled")?.toBoolean() ?: false
//                        val isReschedule = backStackEntry.arguments
//                            ?.getString("isReschedule")?.toBoolean() ?: false
//                        val isStartConsulting = backStackEntry.arguments
//                            ?.getString("isStartConsulting")?.toBoolean() ?: false
//                        val isPreview = backStackEntry.arguments
//                            ?.getString("isPreview")?.toBoolean() ?: false
//
//                        DoctorAppointmentScreen(
//                            navController = navController,
//                            isAppointmentHome = isAppointmentHome,
//                            isCancelled = isCancelled,
//                            isReschedule = isReschedule,
//                            isStartConsulting = isStartConsulting,
//                            isPreview = isPreview
//                        )
//                    }
//
//                    composable(
//                        route = Screen.DoctorSetting.route +
//                                "?isMenuSetting={isMenuSetting}" +
//                                "&isSetting={isSetting}",
//                        arguments = listOf(
//                            navArgument("isMenuSetting") { defaultValue = "false" },
//                            navArgument("isSetting") { defaultValue = "false" },
//
//                            )
//                    ) { backStackEntry ->
//
//                        val isMenuSetting = backStackEntry.arguments
//                            ?.getString("isMenuSetting")?.toBoolean() ?: false
//                        val isSetting = backStackEntry.arguments
//                            ?.getString("isSetting")?.toBoolean() ?: false
//
//
//                        SettingsScreen(
//                            navController = navController,
//                            isMenuSetting = isMenuSetting,
//                            isSetting = isSetting,
//
//                            )
//                    }
//
//
//                    //DoctorAppointmentCancellation
//                    composable(Screen.DoctorAppointmentCancellation.route) {
//                        DoctorAppointmentCancellationScreen(navController)
//
//                    } //DoctorAppointmentReschedule
//                    composable(Screen.DoctorAppointmentReschedule.route) {
//                        DoctorAppointmentRescheduleScreen(navController)
//                    }
//
//
//                    //DoctorChat
//                    composable(Screen.DoctorChat.route) {
//                        DoctorChatScreen(navController)
//                    }
//                    //DoctorChatDetail
//                    composable(
//                        route = Screen.DoctorChatDetail.route,
//                        arguments = listOf(
//                            navArgument("name") { type = NavType.StringType },
//                            navArgument("role") { type = NavType.StringType }
//                        )
//                    ) { backStackEntry ->
//                        val name = backStackEntry.arguments?.getString("name") ?: ""
//                        val role = backStackEntry.arguments?.getString("role") ?: ""
//                        ChatDetailScreen(name, role, navController)
//                    }
//
//
//                    composable(Screen.DoctorSetting.route) {
//                        SettingsScreen(navController)
//                    }
//
//
//                    composable("announcement_screen") {
//                        AnnouncementScreen(navController)
//                    }
//                    composable("logout_screen") {
//                        SettingsScreen(navController)
//                    }
//                }
//            }
//
//        }
//    }
//}
//


package org.ayurtouch.project

import android.annotation.SuppressLint
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import org.ayurtouch.project.doctor.navigationDoctorFlow.Screen
import org.ayurtouch.project.doctor.screens.annoncementScreen.AnnouncementScreen
import org.ayurtouch.project.doctor.screens.appointmentScreen.DoctorAppointmentScreen
import org.ayurtouch.project.doctor.screens.appointmentScreen.cancelation.DoctorAppointmentCancellationScreen
import org.ayurtouch.project.doctor.screens.appointmentScreen.reSchedule.DoctorAppointmentRescheduleScreen
import org.ayurtouch.project.doctor.screens.doctorChatScreen.ChatDetailScreen
import org.ayurtouch.project.doctor.screens.doctorChatScreen.DoctorChatScreen
import org.ayurtouch.project.doctor.screens.homeScreen.HomeScreen
import org.ayurtouch.project.doctor.screens.loginScreen.model.SmsBroadcastReceiver
import org.ayurtouch.project.doctor.screens.loginScreen.view.DoctorLoginScreen
import org.ayurtouch.project.doctor.screens.loginScreen.viewmodel.DoctorLoginViewModel
import org.ayurtouch.project.doctor.screens.mainScreen.DoctorMainScreen
import org.ayurtouch.project.doctor.screens.notificationScreen.NotificationScreen
import org.ayurtouch.project.doctor.screens.settingScreen.SettingsScreen
import org.ayurtouch.project.doctor.screens.splashScreen.SplashScreen

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private val loginViewModel: DoctorLoginViewModel by viewModels()
    private lateinit var smsReceiver: SmsBroadcastReceiver

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onStart() {
        super.onStart()
        smsReceiver = SmsBroadcastReceiver().apply {
            otpReceived = { code ->
                loginViewModel.updateOtp(code)
            }
        }
        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
        registerReceiver(smsReceiver, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(smsReceiver)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()

        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStackEntry?.destination?.route

                // Hide status bar only in Auth screen
                LaunchedEffect(currentRoute) {
                    val controller = window.insetsController
                    if (currentRoute == Screen.Auth.route) {
                        controller?.hide(WindowInsets.Type.statusBars())
                    } else {
                        controller?.show(WindowInsets.Type.statusBars())
                    }
                }

                val TEST_MODE = false
                NavHost(
                    navController = navController,
                    startDestination = if (TEST_MODE) Screen.DoctorMain.route else Screen.DoctorMain.route
                ) {
                    composable(Screen.Splash.route) {
                        SplashScreen(navController)
                    }

                    composable(Screen.Auth.route) {
                        DoctorLoginScreen(
                            activity = this@MainActivity,
                            auth = auth,
                            navController = navController,
                            viewModel = loginViewModel
                        )
                    }

                    composable(Screen.DoctorMain.route) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                            DoctorMainScreen(navController)
                        }
                    }

                    composable(Screen.DoctorHome.route) {
                        HomeScreen(navController)
                    }

                    composable(Screen.DoctorNotification.route) {
                        NotificationScreen(navController)
                    }

                    composable(
                        route = Screen.DoctorAppointment.route +
                                "?isAppointmentHome={isAppointmentHome}" +
                                "&isCancelled={isCancelled}" +
                                "&isReschedule={isReschedule}" +
                                "&isStartConsulting={isStartConsulting}" +
                                "&isPreview={isPreview}",
                        arguments = listOf(
                            navArgument("isAppointmentHome") { defaultValue = "false" },
                            navArgument("isCancelled") { defaultValue = "false" },
                            navArgument("isReschedule") { defaultValue = "false" },
                            navArgument("isStartConsulting") { defaultValue = "false" },
                            navArgument("isPreview") { defaultValue = "false" }
                        )
                    ) { backStackEntry ->
                        DoctorAppointmentScreen(
                            navController = navController,
                            isAppointmentHome = backStackEntry.arguments?.getString("isAppointmentHome")
                                ?.toBooleanStrictOrNull() ?: false,
                            isCancelled = backStackEntry.arguments?.getString("isCancelled")
                                ?.toBooleanStrictOrNull() ?: false,
                            isReschedule = backStackEntry.arguments?.getString("isReschedule")
                                ?.toBooleanStrictOrNull() ?: false,
                            isStartConsulting = backStackEntry.arguments?.getString("isStartConsulting")
                                ?.toBooleanStrictOrNull() ?: false,
                            isPreview = backStackEntry.arguments?.getString("isPreview")
                                ?.toBooleanStrictOrNull() ?: false
                        )
                    }

                    composable(
                        route = Screen.DoctorSetting.route +
                                "?isMenuSetting={isMenuSetting}" +
                                "&isSetting={isSetting}",
                        arguments = listOf(
                            navArgument("isMenuSetting") { defaultValue = "false" },
                            navArgument("isSetting") { defaultValue = "false" }
                        )
                    ) { backStackEntry ->
                        SettingsScreen(
                            navController = navController,
                            isMenuSetting = backStackEntry.arguments?.getString("isMenuSetting")
                                ?.toBooleanStrictOrNull() ?: false,
                            isSetting = backStackEntry.arguments?.getString("isSetting")
                                ?.toBooleanStrictOrNull() ?: false
                        )
                    }

                    composable(Screen.DoctorAppointmentCancellation.route) {
                        DoctorAppointmentCancellationScreen(navController)
                    }

                    composable(Screen.DoctorAppointmentReschedule.route) {
                        DoctorAppointmentRescheduleScreen(navController)
                    }

                    composable(Screen.DoctorChat.route) {
                        DoctorChatScreen(navController)
                    }

                    composable(
                        route = Screen.DoctorChatDetail.route,
                        arguments = listOf(
                            navArgument("name") { type = NavType.StringType },
                            navArgument("role") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        ChatDetailScreen(
                            name = backStackEntry.arguments?.getString("name") ?: "",
                            role = backStackEntry.arguments?.getString("role") ?: "",
                            navController = navController
                        )
                    }

                    composable(Screen.DoctorAnnouncement.route) {
                        AnnouncementScreen(navController)
                    }

                    composable("logout_screen") {
                        SettingsScreen(navController)
                    }
                }
            }
        }
    }
}
