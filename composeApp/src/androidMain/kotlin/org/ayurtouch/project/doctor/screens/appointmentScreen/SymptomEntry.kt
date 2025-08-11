package org.ayurtouch.project.doctor.screens.appointmentScreen


data class SymptomEntry(
    var nature: String = "",
    var aggravatingFactor: String = "",
    var reducingFactor: String = "",
    var onset: String = "",
    var symptomStatus: String = "",
    var intensityScale: String = ""
)