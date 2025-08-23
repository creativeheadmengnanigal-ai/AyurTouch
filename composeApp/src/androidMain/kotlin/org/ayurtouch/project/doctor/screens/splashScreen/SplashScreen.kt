
package org.ayurtouch.project.doctor.screens.splashScreen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import app.rive.runtime.kotlin.RiveAnimationView
import app.rive.runtime.kotlin.core.Loop
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import org.ayurtouch.project.R
import org.ayurtouch.project.doctor.navigationDoctorFlow.Screen

@Composable
fun SplashScreen(navController: NavController) {
    val auth = FirebaseAuth.getInstance()

    LaunchedEffect(Unit) {
        delay(6000) // wait for animation

        val currentUser = auth.currentUser
        if (currentUser != null) {
            // ✅ user already logged in, go to doctor home
            navController.navigate(Screen.DoctorMain.route) {
                popUpTo(Screen.Splash.route) { inclusive = true }
            }
        } else {
            // 🚪 no user, go to login/auth flow
            navController.navigate(Screen.Auth.route) {
                popUpTo(Screen.Splash.route) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                RiveAnimationView(context).apply {
                    setRiveResource(
                        resId = R.raw.doctor_splash,
                        artboardName = "doctor_splash",
                        stateMachineName = "State Machine 1",
                        loop = Loop.ONESHOT
                    )
                    post { fireState("State Machine 1", "Trigger 1") }
                }
            }
        )
    }
}
