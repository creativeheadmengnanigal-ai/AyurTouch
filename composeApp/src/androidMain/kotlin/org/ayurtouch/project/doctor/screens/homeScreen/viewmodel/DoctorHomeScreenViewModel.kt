package org.ayurtouch.project.doctor.screens.homeScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.ayurtouch.project.doctor.screens.homeScreen.model.Doctor
import org.ayurtouch.project.doctor.screens.homeScreen.model.DoctorHomeScreenRepository


class DoctorHomeScreenViewModel : ViewModel() {

    val repo = DoctorHomeScreenRepository()

    private val _doctorHomeScreenStates = MutableStateFlow<DoctorHomeScreenStates>(DoctorHomeScreenStates.Nothing)
    val doctorHomeScreenStates = _doctorHomeScreenStates.asStateFlow()

    fun fetchDoctorInfo(doctorId: String) {
        viewModelScope.launch {
            _doctorHomeScreenStates.value = DoctorHomeScreenStates.Loading
            val result = repo.fetchDoctorInfo(doctorId)
            _doctorHomeScreenStates.value =
                if (result != null) DoctorHomeScreenStates.Success(result) else DoctorHomeScreenStates.Error
        }
    }
}

sealed class DoctorHomeScreenStates {
    object Nothing : DoctorHomeScreenStates()
    object Loading : DoctorHomeScreenStates()
    data class Success(val doctor: Doctor) : DoctorHomeScreenStates()
    object Error : DoctorHomeScreenStates()



}