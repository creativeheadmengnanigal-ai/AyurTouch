package org.ayurtouch.project.doctor.screens.loginScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.ayurtouch.project.MainActivity
import org.ayurtouch.project.doctor.screens.loginScreen.model.DoctorAuthRepository

data class DoctorLoginUiState(
    val phoneNumber: String = "",
    val otp: String = "",
    val isOtpSent: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val verificationId: String? = null,
    val loginSuccess: Boolean = false
)

class DoctorLoginViewModel(
    private val repository: DoctorAuthRepository = DoctorAuthRepository(FirebaseAuth.getInstance())
) : ViewModel() {

    private val _uiState = MutableStateFlow(DoctorLoginUiState())
    val uiState: StateFlow<DoctorLoginUiState> = _uiState

    fun updatePhoneNumber(number: String) {
        _uiState.value = _uiState.value.copy(phoneNumber = number)
    }

    fun updateOtp(otp: String) {
        _uiState.value = _uiState.value.copy(otp = otp)
    }

    fun sendOtp(activity: MainActivity) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            repository.sendOtp(_uiState.value.phoneNumber, activity).collect { result ->
                result.onSuccess { data ->
                    if (data.startsWith("AUTO_VERIFIED:")) {
                        _uiState.value = _uiState.value.copy(
                            otp = data.removePrefix("AUTO_VERIFIED:"),
                            loginSuccess = true,
                            isLoading = false
                        )
                    } else {
                        _uiState.value = _uiState.value.copy(
                            verificationId = data,
                            isOtpSent = true,
                            isLoading = false
                        )
                    }
                }.onFailure { e ->
                    _uiState.value = _uiState.value.copy(
                        errorMessage = e.message,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun verifyOtp() {
        val verificationId = _uiState.value.verificationId ?: return
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            repository.verifyOtp(verificationId, _uiState.value.otp).collect { result ->
                result.onSuccess {
                    _uiState.value = _uiState.value.copy(
                        loginSuccess = true,
                        isLoading = false
                    )
                }.onFailure { e ->
                    _uiState.value = _uiState.value.copy(
                        errorMessage = e.message,
                        isLoading = false
                    )
                }
            }
        }
    }
}
