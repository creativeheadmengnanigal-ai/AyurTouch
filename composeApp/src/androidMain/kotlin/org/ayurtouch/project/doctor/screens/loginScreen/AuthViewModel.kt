//package org.ayurtouch.project.doctor.screens.loginScreen
//
//import android.app.Activity
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.google.firebase.FirebaseException
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.PhoneAuthCredential
//import com.google.firebase.auth.PhoneAuthOptions
//import com.google.firebase.auth.PhoneAuthProvider
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.launch
//import java.util.concurrent.TimeUnit
//
//class AuthViewModel : ViewModel() {
//    private val auth = FirebaseAuth.getInstance()
//
//    private var verificationId: String? = null
//    private var resendToken: PhoneAuthProvider.ForceResendingToken? = null
//
//    private val _isOtpSent = MutableStateFlow(false)
//    val isOtpSent = _isOtpSent.asStateFlow()
//
//    private val _isLoading = MutableStateFlow(false)
//    val isLoading = _isLoading.asStateFlow()
//
//    private val _errorMessage = MutableStateFlow<String?>(null)
//    val errorMessage = _errorMessage.asStateFlow()
//
//    fun sendOtp(phoneNumber: String, activity: Activity) {
//        viewModelScope.launch {
//            _isLoading.value = true
//            val options = PhoneAuthOptions.newBuilder(auth)
//                .setPhoneNumber("+91$phoneNumber") // India code, adjust if needed
//                .setTimeout(60L, TimeUnit.SECONDS)
//                .setActivity(activity)
//                .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//                        signInWithCredential(credential)
//                    }
//
//                    override fun onVerificationFailed(e: FirebaseException) {
//                        _isLoading.value = false
//                        _errorMessage.value = e.message
//                    }
//
//                    override fun onCodeSent(
//                        verificationId: String,
//                        token: PhoneAuthProvider.ForceResendingToken
//                    ) {
//                        _isLoading.value = false
//                        _isOtpSent.value = true
//                        this@AuthViewModel.verificationId = verificationId
//                        resendToken = token
//                    }
//                })
//                .build()
//            PhoneAuthProvider.verifyPhoneNumber(options)
//        }
//    }
//
//    fun verifyOtp(otp: String, onSuccess: () -> Unit) {
//        val credential = PhoneAuthProvider.getCredential(verificationId ?: "", otp)
//        signInWithCredential(credential, onSuccess)
//    }
//
//    private fun signInWithCredential(
//        credential: PhoneAuthCredential,
//        onSuccess: (() -> Unit)? = null
//    ) {
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener { task ->
//                _isLoading.value = false
//                if (task.isSuccessful) {
//                    onSuccess?.invoke()
//                } else {
//                    _errorMessage.value = task.exception?.message
//                }
//            }
//    }
//}


package org.ayurtouch.project.doctor.screens.loginScreen

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    private var verificationId: String? = null
    private var resendToken: PhoneAuthProvider.ForceResendingToken? = null

    private val _uiState = MutableStateFlow(AuthState())
    val uiState = _uiState.asStateFlow()

    fun sendOtp(phoneNumber: String, activity: Activity) {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber("+91$phoneNumber") // Indian number
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    // Auto-retrieval or instant verification
                    credential.smsCode?.let { code ->
                        _uiState.value = _uiState.value.copy(otp = code)
                        verifyOtp(code)
                    }
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    _uiState.value = _uiState.value.copy(isLoading = false, error = e.message)
                }

                override fun onCodeSent(vid: String, token: PhoneAuthProvider.ForceResendingToken) {
                    verificationId = vid
                    resendToken = token
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isOtpSent = true
                    )
                }
            })
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun verifyOtp(otp: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, otp)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                saveUserToFirestore()
            } else {
                _uiState.value = _uiState.value.copy(error = task.exception?.message)
            }
        }
    }

    private fun saveUserToFirestore() {
        viewModelScope.launch {
            val uid = auth.currentUser?.uid ?: return@launch
            val phone = auth.currentUser?.phoneNumber

            val userMap = hashMapOf(
                "uid" to uid,
                "phone" to phone,
                "createdAt" to System.currentTimeMillis()
            )

            firestore.collection("users").document(uid).set(userMap)
                .addOnSuccessListener {
                    _uiState.value = _uiState.value.copy(isSuccess = true)
                }
                .addOnFailureListener {
                    _uiState.value = _uiState.value.copy(error = it.message)
                }
        }
    }
}

