package org.ayurtouch.project.doctor.screens.loginScreen



data class AuthState(
    val isLoading: Boolean = false,
    val isOtpSent: Boolean = false,
    val otp: String = "",
    val isSuccess: Boolean = false,
    val error: String? = null
)
