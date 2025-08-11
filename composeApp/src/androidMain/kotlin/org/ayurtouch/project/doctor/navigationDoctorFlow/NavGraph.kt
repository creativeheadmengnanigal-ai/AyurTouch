package org.ayurtouch.project.doctor.navigationDoctorFlow

import android.net.Uri
import androidx.compose.runtime.mutableStateOf


sealed class Screen(val route: String) {

    object Splash : Screen("splash")

    object Auth : Screen("auth")

    object DoctorMain: Screen("doctor_main")

    object DoctorHome : Screen("doctor_home")

    object DoctorAppointment:Screen("doctor_appointment")

    object DoctorAppointmentCancellation: Screen(   "doctor_appointment_cancellation")

    object DoctorAppointmentReschedule: Screen("doctor_appointment_reschedule")

    object DoctorSetting :Screen("doctor_setting")

    object DoctorAnnouncement:Screen("announcement_screen")

    object DoctorChat:Screen("chat_screen")

    // New one
    object DoctorChatDetail : Screen("chat_detail_screen/{name}/{role}") {
        fun createRoute(name: String, role: String) = "chat_detail_screen/$name/$role"
    }


}

