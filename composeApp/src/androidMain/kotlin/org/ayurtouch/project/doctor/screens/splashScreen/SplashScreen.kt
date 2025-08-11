
package org.ayurtouch.project.doctor.screens.splashScreen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import app.rive.runtime.kotlin.RiveAnimationView
import app.rive.runtime.kotlin.core.Loop

import kotlinx.coroutines.delay
import org.ayurtouch.project.R

@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(Unit) {
        delay(6000) // match your animation length
        navController.navigate("auth") {
            popUpTo("splash") { inclusive = true }
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

                    // Start the state machine trigger
                    post {
                        fireState("State Machine 1", "Trigger 1")
                    }
                }
            }
        )
    }
}
