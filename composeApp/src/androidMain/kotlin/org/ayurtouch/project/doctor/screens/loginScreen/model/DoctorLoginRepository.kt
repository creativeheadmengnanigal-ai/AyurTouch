package org.ayurtouch.project.doctor.screens.loginScreen.model

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import org.ayurtouch.project.doctor.screens.homeScreen.model.Doctor

class DoctorLoginRepository {

    private val firebaseFirestore = FirebaseFirestore.getInstance()

    suspend fun saveDoctorInfo(doctor: Doctor): Boolean {
        return try {
            doctor.doctorId?.let { doctorId ->
                firebaseFirestore.collection("doctors")
                    .document(doctorId)
                    .set(doctor)
                    .await()
                true
            } ?: false
        } catch (e: Exception) {
            println("Error saving doctor info: $e")
            false

        }
    }


}
