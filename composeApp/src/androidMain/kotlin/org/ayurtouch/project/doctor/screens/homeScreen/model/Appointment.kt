package org.ayurtouch.project.doctor.screens.homeScreen.model

import kotlinx.serialization.Serializable

@Serializable
data class Appointment(
    val time: String,
    val date: String,
    val consultingType: String,
    val patientName: String,
    val status: String
)