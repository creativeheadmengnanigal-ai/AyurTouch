package org.ayurtouch.project.doctor.screens.loginScreen.model

data class DoctorLoginUiState(
    val phoneNumber: String = "",
    val otp: String = "",
    val isOtpSent: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val verificationId: String? = null,
    val loginSuccess: Boolean = false
)

