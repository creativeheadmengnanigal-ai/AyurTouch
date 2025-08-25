package org.ayurtouch.project.doctor.screens.loginScreen.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.ayurtouch.project.doctor.screens.loginScreen.model.DoctorAuthRepository


data class DoctorLoginUiState(
    val phoneNumber: String = "",
    val otp: String = "",
    val isOtpSent: Boolean = false,
    val verificationId: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val user: FirebaseUser? = null,
    val message: String?=null
)

class DoctorLoginViewModel(
    private val repository: DoctorAuthRepository = DoctorAuthRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(DoctorLoginUiState())
    val uiState: StateFlow<DoctorLoginUiState> = _uiState.asStateFlow()

    fun updatePhoneNumber(number: String) {
        _uiState.update { it.copy(phoneNumber = number,) }
    }

    fun updateOtp(otp: String) {
        _uiState.update { it.copy(otp = otp,) }
    }

    fun sendOtp(activity: Activity) {
        var phoneNumber = _uiState.value.phoneNumber.trim()

        if (phoneNumber.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Phone number required") }
            return
        }

        // Ensure E.164 format (example: +91 for India)
        if (!phoneNumber.startsWith("+")) {
            phoneNumber = "+91$phoneNumber"  // 👈 add your country code here
        }

        viewModelScope.launch {
            repository.sendOtp(phoneNumber, activity).collect { result ->
                result.onSuccess { verificationId ->
                    if (verificationId.startsWith("AUTO_VERIFIED")) {
                        val otp = verificationId.split(":")[1]
                        updateOtp(otp)
                    } else {
                        _uiState.update { it.copy(isOtpSent = true, verificationId = verificationId) }
                    }
                }.onFailure {
                    _uiState.update { it.copy(errorMessage = it.message) }
                }
            }
        }
    }


    fun verifyOtp() {
        val state = _uiState.value
        val otp = state.otp
        val verificationId = state.verificationId ?: return

        viewModelScope.launch {
            repository.verifyOtp(verificationId, otp).collect { result ->
                result.onSuccess { user ->
                    _uiState.update { it.copy(user = user,) }
                }.onFailure {
                    _uiState.update { it.copy(errorMessage = it.message,) }
                }
            }
        }
    }
}
