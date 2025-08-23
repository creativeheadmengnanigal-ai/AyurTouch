////package org.ayurtouch.project.doctor.screens.loginScreen.viewmodel
////
////import androidx.lifecycle.ViewModel
////import androidx.lifecycle.viewModelScope
////import com.google.firebase.auth.FirebaseAuth
////import com.google.firebase.firestore.FirebaseFirestore
////import kotlinx.coroutines.flow.MutableStateFlow
////import kotlinx.coroutines.flow.StateFlow
////import kotlinx.coroutines.launch
////import org.ayurtouch.project.MainActivity
////import org.ayurtouch.project.doctor.screens.loginScreen.model.DoctorAuthRepository
////
////data class DoctorLoginUiState(
////    val phoneNumber: String = "",
////    val otp: String = "",
////    val isOtpSent: Boolean = false,
////    val isLoading: Boolean = false,
////    val errorMessage: String? = null,
////    val verificationId: String? = null,
////    val loginSuccess: Boolean = false
////)
////
////class DoctorLoginViewModel(
////    private val repository: DoctorAuthRepository = DoctorAuthRepository(FirebaseAuth.getInstance())
////) : ViewModel() {
////
////    private val _uiState = MutableStateFlow(DoctorLoginUiState())
////    val uiState: StateFlow<DoctorLoginUiState> = _uiState
////
////    fun updatePhoneNumber(number: String) {
////        _uiState.value = _uiState.value.copy(phoneNumber = number)
////    }
////
////    fun updateOtp(otp: String) {
////        _uiState.value = _uiState.value.copy(otp = otp)
////    }
////
////    fun sendOtp(activity: MainActivity) {
////        viewModelScope.launch {
////            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
////            repository.sendOtp(_uiState.value.phoneNumber, activity).collect { result ->
////                result.onSuccess { data ->
////                    if (data.startsWith("AUTO_VERIFIED:")) {
////                        _uiState.value = _uiState.value.copy(
////                            otp = data.removePrefix("AUTO_VERIFIED:"),
////                            loginSuccess = true,
////                            isLoading = false
////                        )
////                    } else {
////                        _uiState.value = _uiState.value.copy(
////                            verificationId = data,
////                            isOtpSent = true,
////                            isLoading = false
////                        )
////                    }
////                }.onFailure { e ->
////                    _uiState.value = _uiState.value.copy(
////                        errorMessage = e.message,
////                        isLoading = false
////                    )
////                }
////            }
////        }
////    }
////
////
////
////    fun verifyOtp() {
////        val verificationId = _uiState.value.verificationId ?: return
////        viewModelScope.launch {
////            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
////            repository.verifyOtp(verificationId, _uiState.value.otp).collect { result ->
////                result.onSuccess { authResult ->
////                    val user = authResult.user
////                    user?.let {
////                        val db = FirebaseFirestore.getInstance()
////                        val doctor = hashMapOf(
////                            "uid" to it.uid,
////                            "phoneNumber" to _uiState.value.phoneNumber,
////                            "role" to "doctor",
////                            "createdAt" to System.currentTimeMillis()
////                        )
////                        db.collection("users").document(it.uid).set(doctor)
////                    }
////
////                    _uiState.value = _uiState.value.copy(
////                        loginSuccess = true,
////                        isLoading = false
////                    )
////                }.onFailure { e ->
////                    _uiState.value = _uiState.value.copy(
////                        errorMessage = e.message,
////                        isLoading = false
////                    )
////                }
////            }
////        }
////    }
////
////}
//
//
//package org.ayurtouch.project.doctor.screens.loginScreen.viewmodel
//
//import android.util.Log
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.firestore.FirebaseFirestore
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//import org.ayurtouch.project.MainActivity
//import org.ayurtouch.project.doctor.screens.loginScreen.model.DoctorAuthRepository
//import org.ayurtouch.project.doctor.screens.loginScreen.model.DoctorLoginUiState
//
//
//class DoctorLoginViewModel(
//    private val repository: DoctorAuthRepository = DoctorAuthRepository(FirebaseAuth.getInstance())
//) : ViewModel() {
//
//    private val _uiState = MutableStateFlow(DoctorLoginUiState())
//    val uiState: StateFlow<DoctorLoginUiState> = _uiState
//
//    fun updatePhoneNumber(number: String) {
//        Log.d("DoctorLoginVM", "Phone number updated: $number")
//        _uiState.value = _uiState.value.copy(phoneNumber = number)
//    }
//
//    fun updateOtp(otp: String) {
//        Log.d("DoctorLoginVM", "OTP updated: $otp")
//        _uiState.value = _uiState.value.copy(otp = otp)
//    }
//
//    fun sendOtp(activity: MainActivity) {
//        val phone = _uiState.value.phoneNumber
//        if (phone.length != 10) {
//            _uiState.value = _uiState.value.copy(errorMessage = "Enter valid phone number")
//            return
//        }
//
//        viewModelScope.launch {
//            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
//            repository.sendOtp(phone, activity).collect { result ->
//                result.onSuccess { data ->
//                    when {
//                        data.startsWith("AUTO_VERIFIED:") -> {
//                            val code = data.removePrefix("AUTO_VERIFIED:")
//                            Log.d("DoctorLoginVM", "Auto verified OTP: $code")
//                            _uiState.value = _uiState.value.copy(
//                                otp = code,
//                                loginSuccess = true,
//                                isLoading = false
//                            )
//                        }
//                        data == "AUTO_VERIFIED" -> {
//                            Log.d("DoctorLoginVM", "Auto verified without OTP")
//                            _uiState.value = _uiState.value.copy(
//                                loginSuccess = true,
//                                isLoading = false
//                            )
//                        }
//                        else -> {
//                            Log.d("DoctorLoginVM", "OTP Sent. VerificationId=$data")
//                            _uiState.value = _uiState.value.copy(
//                                verificationId = data,
//                                isOtpSent = true,
//                                isLoading = false
//                            )
//                        }
//                    }
//                }.onFailure { e ->
//                    Log.e("DoctorLoginVM", "OTP sending failed", e)
//                    _uiState.value = _uiState.value.copy(
//                        errorMessage = e.message,
//                        isLoading = false
//                    )
//                }
//            }
//        }
//    }
//
//    fun verifyOtp() {
//        val verificationId = _uiState.value.verificationId ?: return
//        val otp = _uiState.value.otp
//
//        if (otp.length != 6) {
//            _uiState.value = _uiState.value.copy(errorMessage = "Enter valid 6-digit OTP")
//            return
//        }
//
//        viewModelScope.launch {
//            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
//            repository.verifyOtp(verificationId, otp).collect { result ->
//                result.onSuccess { authResult ->
//                    val user = authResult.user
//                    Log.d("DoctorLoginVM", "OTP Verified. User UID=${user?.uid}")
//
//                    user?.let {
//                        val db = FirebaseFirestore.getInstance()
//                        val doctor = hashMapOf(
//                            "uid" to it.uid,
//                            "phoneNumber" to _uiState.value.phoneNumber,
//                            "role" to "doctor",
//                            "createdAt" to System.currentTimeMillis()
//                        )
//                        db.collection("users").document(it.uid).set(doctor)
//                            .addOnSuccessListener { Log.d("DoctorLoginVM", "Doctor saved in Firestore") }
//                            .addOnFailureListener { e -> Log.e("DoctorLoginVM", "Failed to save doctor", e) }
//                    }
//
//                    _uiState.value = _uiState.value.copy(
//                        loginSuccess = true,
//                        isLoading = false
//                    )
//                }.onFailure { e ->
//                    Log.e("DoctorLoginVM", "OTP verification failed", e)
//                    _uiState.value = _uiState.value.copy(
//                        errorMessage = e.message,
//                        isLoading = false
//                    )
//                }
//            }
//        }
//    }
//}


package org.ayurtouch.project.doctor.screens.loginScreen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.ayurtouch.project.MainActivity
import org.ayurtouch.project.doctor.screens.loginScreen.model.Doctor
import org.ayurtouch.project.doctor.screens.loginScreen.model.DoctorAuthRepository
import org.ayurtouch.project.doctor.screens.loginScreen.model.DoctorLoginUiState

class DoctorLoginViewModel(
    private val repository: DoctorAuthRepository = DoctorAuthRepository(FirebaseAuth.getInstance())
) : ViewModel() {

    private val _uiState = MutableStateFlow(DoctorLoginUiState())
    val uiState: StateFlow<DoctorLoginUiState> = _uiState

    // --- State Updates ---
    fun updatePhoneNumber(number: String) {
        log("Phone number updated: $number")
        setState { copy(phoneNumber = number) }
    }

    fun updateOtp(otp: String) {
        log("OTP updated: $otp")
        setState { copy(otp = otp) }
    }

    // --- Send OTP ---
    fun sendOtp(activity: MainActivity) {
        val phone = uiState.value.phoneNumber

        // Validation
        if (phone.length != 10) {
            setState { copy(errorMessage = "Enter valid phone number") }
            return
        }

        viewModelScope.launch {
            setState { copy(isLoading = true, errorMessage = null) }

            repository.sendOtp(phone, activity).collect { result ->
                result.onSuccess { data ->
                    when {
                        data.startsWith("AUTO_VERIFIED:") -> {
                            val code = data.removePrefix("AUTO_VERIFIED:")
                            log("Auto verified OTP: $code")
                            setState {
                                copy(
                                    otp = code,
                                    loginSuccess = true,
                                    isLoading = false
                                )
                            }
                        }
                        data == "AUTO_VERIFIED" -> {
                            log("Auto verified without OTP")
                            setState { copy(loginSuccess = true, isLoading = false) }
                        }
                        else -> {
                            log("OTP Sent. VerificationId=$data")
                            setState {
                                copy(
                                    verificationId = data,
                                    isOtpSent = true,
                                    isLoading = false
                                )
                            }
                        }
                    }
                }.onFailure { e ->
                    error("OTP sending failed", e)
                    setState { copy(errorMessage = e.message, isLoading = false) }
                }
            }
        }
    }

    // --- Verify OTP ---
    fun verifyOtp() {
        val verificationId = uiState.value.verificationId ?: return
        val otp = uiState.value.otp

        // Validation
        if (otp.length != 6) {
            setState { copy(errorMessage = "Enter valid 6-digit OTP") }
            return
        }

        viewModelScope.launch {
            setState { copy(isLoading = true, errorMessage = null) }

            repository.verifyOtp(verificationId, otp).collect { result ->
                result.onSuccess { authResult ->
                    val user = authResult.user
                    log("OTP Verified. User UID=${user?.uid}")

                    user?.let {
                        saveDoctorToFirestore(it.uid, uiState.value.phoneNumber)
                    }

                    setState { copy(loginSuccess = true, isLoading = false) }
                }.onFailure { e ->
                    error("OTP verification failed", e)
                    setState { copy(errorMessage = e.message, isLoading = false) }
                }
            }
        }
    }

    // --- Save Doctor to Firestore ---
    private fun saveDoctorToFirestore(uid: String, phone: String) {
        val db = FirebaseFirestore.getInstance()

        // Create Doctor object using the data class constructor
        val doctor = Doctor(
            uid = uid,
            phoneNumber = phone,
            role = "doctor",
            createdAt = System.currentTimeMillis()
        )

        // Save to Firestore
        db.collection("users").document(uid).set(doctor)
            .addOnSuccessListener {
                log("Doctor saved in Firestore")
            }
            .addOnFailureListener { e ->
                error("Failed to save doctor", e)
            }
    }

    // --- Helpers ---
    private fun setState(reducer: DoctorLoginUiState.() -> DoctorLoginUiState) {
        _uiState.value = _uiState.value.reducer()
    }

    private fun log(msg: String) {
        Log.d("DoctorLoginVM", msg)
    }

    private fun error(msg: String, e: Throwable? = null) {
        Log.e("DoctorLoginVM", msg, e)
    }
}
