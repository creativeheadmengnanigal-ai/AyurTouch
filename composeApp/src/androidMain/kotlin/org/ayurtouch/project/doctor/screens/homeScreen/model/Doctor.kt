package org.ayurtouch.project.doctor.screens.homeScreen.model

import com.google.firebase.Timestamp

data class Doctor(
    val name: String? = "",
    val email: String? = "",
    val phone: String? = "",
    val role: String? = "",
    val password: String? = "",
    val createdAt: Timestamp? = null,
    val profileImage: String? = "",
    val doctorId: String? = ""
)
