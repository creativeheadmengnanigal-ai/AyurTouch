package org.ayurtouch.project.doctor.screens.appointmentScreen

import android.net.wifi.hotspot2.pps.HomeSp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ayurtouch.composeapp.generated.resources.*
import org.ayurtouch.project.AppColors
import org.ayurtouch.project.Monsetserrat400
import org.ayurtouch.project.Monsetserrat500
import org.ayurtouch.project.Monsetserrat600
import org.ayurtouch.project.doctor.navigationDoctorFlow.Screen
import org.ayurtouch.project.doctor.screens.appointmentScreen.cancelation.IsCancellationProcessUI
import org.ayurtouch.project.doctor.screens.appointmentScreen.doctorConsulting.IsConsultingProcessUI
import org.ayurtouch.project.doctor.screens.appointmentScreen.reSchedule.IsRescheduleProcessUI
import org.ayurtouch.project.doctor.utils.*
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun DoctorAppointmentScreenPreview() {
    val navController = rememberNavController()

    DoctorAppointmentScreen(navController)
}

@Composable
fun DoctorAppointmentScreen(
    navController: NavController,
    isAppointmentHome: Boolean=false,
    isCancelled: Boolean = false,
    isReschedule: Boolean = false,
    isStartConsulting: Boolean = false,
    isPreview: Boolean = false
) {

    Scaffold(
        topBar = { CustomTopAppBar("") }
    ) { innerPadding ->


        if (isAppointmentHome){
            IsAppointmentHomeUI(innerPadding, navController)
        }

        if (isCancelled) {
            IsCancellationProcessUI(innerPadding, navController)
        }


        if (isReschedule) {
            IsRescheduleProcessUI(innerPadding, navController)
        }

        if (isStartConsulting){
            IsConsultingProcessUI(innerPadding, navController)
        }

    }
}






