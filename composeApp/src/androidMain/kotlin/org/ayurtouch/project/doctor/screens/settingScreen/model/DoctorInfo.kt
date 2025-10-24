package org.ayurtouch.project.doctor.screens.settingScreen.model

data class DoctorInfo(
    val name: String = "",
    val yearOfExperience: String = "",
    val language: String = "",
    val clinicalInterest: String = "",
    val location: String = "",
    val education: List<String> = emptyList(),
    val moreAboutPhysician: String = "",
    val moreClinicalInterest: List<String> = emptyList(),
)