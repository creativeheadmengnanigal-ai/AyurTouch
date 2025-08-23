package org.ayurtouch.project.doctor.screens.loginScreen.model


data class Doctor(
    val uid: String = "",
    val phoneNumber: String = "",
    val role: String = "doctor",
    val createdAt: Long = 0L
)
