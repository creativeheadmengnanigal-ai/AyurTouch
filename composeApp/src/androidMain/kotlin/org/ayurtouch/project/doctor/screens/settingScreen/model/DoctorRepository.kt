package org.ayurtouch.project.doctor.screens.settingScreen.model

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import org.ayurtouch.project.doctor.screens.homeScreen.model.DoctorInfo



class DoctorRepository {

    private val firestore = FirebaseFirestore.getInstance()

    suspend fun getDoctorInfo(): DoctorInfo? {
        return try {
            val snapshot = firestore.collection("doctor_info")
                .document("yvAjKWBiyoexX8MPBcVN")
                .get()
                .await()

            snapshot.toObject(DoctorInfo::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

