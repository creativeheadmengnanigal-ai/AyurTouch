
package org.ayurtouch.project.doctor.screens.loginScreen.model

import android.app.Activity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.concurrent.TimeUnit

class DoctorAuthRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    fun sendOtp(phoneNumber: String, activity: Activity): Flow<Result<String>> = callbackFlow {
        FirebaseAuth.getInstance().firebaseAuthSettings
            .setAppVerificationDisabledForTesting(true)

        val options = PhoneAuthOptions.newBuilder(auth)

            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    trySend(Result.success("AUTO_VERIFIED:${credential.smsCode ?: ""}"))
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    trySend(Result.failure(e))
                }

                override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                    trySend(Result.success(verificationId))
                }
            })
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)

        awaitClose { }
    }

    fun verifyOtp(verificationId: String, otp: String): Flow<Result<FirebaseUser>> = callbackFlow {
        val credential = PhoneAuthProvider.getCredential(verificationId, otp)

        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    if (user != null) {
                        // Save user to Firestore
                        val doctorData = mapOf(
                            "uid" to user.uid,
                            "phoneNumber" to user.phoneNumber,
                            "createdAt" to System.currentTimeMillis()
                        )

                        firestore.collection("doctors")
                            .document(user.uid)
                            .set(doctorData)
                            .addOnSuccessListener {
                                trySend(Result.success(user))
                            }
                            .addOnFailureListener {
                                trySend(Result.failure(it))
                            }
                    }
                } else {
                    trySend(Result.failure(task.exception ?: Exception("Unknown error")))
                }
            }

        awaitClose { }
    }
}
