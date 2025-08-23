//package org.ayurtouch.project.doctor.screens.loginScreen.model
//
//import android.util.Log
//import com.google.android.gms.auth.api.phone.SmsRetriever
//import com.google.firebase.FirebaseException
//import com.google.firebase.auth.AuthResult
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.PhoneAuthCredential
//import com.google.firebase.auth.PhoneAuthOptions
//import com.google.firebase.auth.PhoneAuthProvider
//import kotlinx.coroutines.channels.awaitClose
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.callbackFlow
//import org.ayurtouch.project.MainActivity
//import java.util.concurrent.TimeUnit
//
//class DoctorAuthRepository(private val auth: FirebaseAuth) {
//
//    fun sendOtp(phoneNumber: String, activity: MainActivity): Flow<Result<String>> = callbackFlow {
//        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//                val smsCode = credential.smsCode
//                if (!smsCode.isNullOrEmpty()) {
//                    trySend(Result.success("AUTO_VERIFIED:$smsCode"))
//                } else {
//                    trySend(Result.success("AUTO_VERIFIED"))
//                }
//            }
//
//            override fun onVerificationFailed(e: FirebaseException) {
//                trySend(Result.failure(e))
//            }
//
//            override fun onCodeSent(
//                verificationId: String,
//                token: PhoneAuthProvider.ForceResendingToken
//            ) {
//                trySend(Result.success(verificationId))
//            }
//        }
//
//        val options = PhoneAuthOptions.newBuilder(auth)
//            .setPhoneNumber("+91$phoneNumber")
//            .setTimeout(60L, TimeUnit.SECONDS)
//            .setActivity(activity)
//            .setCallbacks(callbacks)
//            .build()   // ✅ removed .setForceResendingToken(null)
//
//        PhoneAuthProvider.verifyPhoneNumber(options)
//
//        // Start SMS Retriever
//        val client = SmsRetriever.getClient(activity)
//        client.startSmsRetriever()
//            .addOnSuccessListener { Log.d("OTP", "SMS Retriever started") }
//            .addOnFailureListener { Log.e("OTP", "Failed to start SMS retriever", it) }
//
//        awaitClose { }
//    }
//
//    fun verifyOtp(verificationId: String, otp: String): Flow<Result<AuthResult>> = callbackFlow {
//        val credential = PhoneAuthProvider.getCredential(verificationId, otp)
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    trySend(Result.success(task.result!!))
//                } else {
//                    trySend(Result.failure(task.exception ?: Exception("Invalid OTP")))
//                }
//            }
//        awaitClose { }
//    }
//}

//
//package org.ayurtouch.project.doctor.screens.loginScreen.model
//
//import android.util.Log
//import com.google.android.gms.auth.api.phone.SmsRetriever
//import com.google.firebase.FirebaseException
//import com.google.firebase.auth.AuthResult
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.PhoneAuthCredential
//import com.google.firebase.auth.PhoneAuthOptions
//import com.google.firebase.auth.PhoneAuthProvider
//import kotlinx.coroutines.channels.awaitClose
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.callbackFlow
//import org.ayurtouch.project.MainActivity
//import java.util.concurrent.TimeUnit
//
//class DoctorAuthRepository(private val auth: FirebaseAuth) {
//
//    fun sendOtp(phoneNumber: String, activity: MainActivity): Flow<Result<String>> = callbackFlow {
//        Log.d("DoctorAuthRepo", "Sending OTP to: +91$phoneNumber")
//
//        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//                val smsCode = credential.smsCode
//                Log.d("DoctorAuthRepo", "Verification completed. Code=$smsCode")
//                if (!smsCode.isNullOrEmpty()) {
//                    trySend(Result.success("AUTO_VERIFIED:$smsCode"))
//                } else {
//                    trySend(Result.success("AUTO_VERIFIED"))
//                }
//            }
//
//            override fun onVerificationFailed(e: FirebaseException) {
//                Log.e("DoctorAuthRepo", "Verification failed", e)
//                trySend(Result.failure(e))
//            }
//
//            override fun onCodeSent(
//                verificationId: String,
//                token: PhoneAuthProvider.ForceResendingToken
//            ) {
//                Log.d("DoctorAuthRepo", "Code sent. VerificationId=$verificationId")
//                trySend(Result.success(verificationId))
//            }
//        }
//
//        val options = PhoneAuthOptions.newBuilder(auth)
//            .setPhoneNumber("+91$phoneNumber")
//            .setTimeout(60L, TimeUnit.SECONDS)
//            .setActivity(activity)
//            .setCallbacks(callbacks)
//            .build()
//
//        PhoneAuthProvider.verifyPhoneNumber(options)
//
//        // Start SMS Retriever
//        val client = SmsRetriever.getClient(activity)
//        client.startSmsRetriever()
//            .addOnSuccessListener { Log.d("DoctorAuthRepo", "SMS Retriever started") }
//            .addOnFailureListener { Log.e("DoctorAuthRepo", "Failed to start SMS retriever", it) }
//
//        awaitClose { }
//    }
//
//    fun verifyOtp(verificationId: String, otp: String): Flow<Result<AuthResult>> = callbackFlow {
//        Log.d("DoctorAuthRepo", "Verifying OTP: $otp for VerificationId=$verificationId")
//
//        val credential = PhoneAuthProvider.getCredential(verificationId, otp)
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    Log.d("DoctorAuthRepo", "OTP verification success")
//                    trySend(Result.success(task.result!!))
//                } else {
//                    Log.e("DoctorAuthRepo", "OTP verification failed", task.exception)
//                    trySend(Result.failure(task.exception ?: Exception("Invalid OTP")))
//                }
//            }
//        awaitClose { }
//    }
//}


package org.ayurtouch.project.doctor.screens.loginScreen.model

import android.util.Log
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.ayurtouch.project.MainActivity
import java.util.concurrent.TimeUnit

class DoctorAuthRepository(private val auth: FirebaseAuth) {

    // --- Send OTP ---
    fun sendOtp(phoneNumber: String, activity: MainActivity): Flow<Result<String>> = callbackFlow {
        log("Sending OTP to: +91$phoneNumber")

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                val smsCode = credential.smsCode
                log("Verification completed. Code=$smsCode")

                if (!smsCode.isNullOrEmpty()) {
                    trySend(Result.success("AUTO_VERIFIED:$smsCode"))
                } else {
                    trySend(Result.success("AUTO_VERIFIED"))
                }
            }

            override fun onVerificationFailed(e: FirebaseException) {
                error("Verification failed", e)
                trySend(Result.failure(e))
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                log("Code sent. VerificationId=$verificationId")
                trySend(Result.success(verificationId))
            }
        }

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber("+91$phoneNumber")
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callbacks)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)

        // Start SMS Retriever (for auto OTP read)
        SmsRetriever.getClient(activity)
            .startSmsRetriever()
            .addOnSuccessListener { log("SMS Retriever started") }
            .addOnFailureListener { e -> error("Failed to start SMS retriever", e) }

        awaitClose { close() }
    }

    // --- Verify OTP ---
    fun verifyOtp(verificationId: String, otp: String): Flow<Result<AuthResult>> = callbackFlow {
        log("Verifying OTP: $otp for VerificationId=$verificationId")

        val credential = PhoneAuthProvider.getCredential(verificationId, otp)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    log("OTP verification success")
                    trySend(Result.success(task.result!!))
                } else {
                    error("OTP verification failed", task.exception)
                    trySend(Result.failure(task.exception ?: Exception("Invalid OTP")))
                }
            }

        awaitClose { close() }
    }

    // --- Helpers ---
    private fun log(msg: String) = Log.d("DoctorAuthRepo", msg)
    private fun error(msg: String, e: Throwable? = null) = Log.e("DoctorAuthRepo", msg, e)
}
