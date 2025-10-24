package org.ayurtouch.project.doctor.screens.homeScreen.model

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class DoctorHomeScreenRepository {

    val fireStore= FirebaseFirestore.getInstance()

    suspend fun fetchDoctorInfo(doctorId: String):Doctor?{
        return try {
            val snapshot=fireStore.collection("doctors")
                .document(doctorId)
                .get()
                .await()
            snapshot.toObject(Doctor::class.java)
        }
        catch (e:Exception)
        {
            println("Error fetching doctor info: ${e.message}")
            null

        }


    }
}