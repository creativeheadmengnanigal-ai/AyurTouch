package org.ayurtouch.project.doctor.screens.settingScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.ayurtouch.project.doctor.screens.homeScreen.model.DoctorInfo
import org.ayurtouch.project.doctor.screens.settingScreen.model.DoctorRepository


class DoctorSettingViewModel : ViewModel() {

    private val repository = DoctorRepository()

    private val _doctorInfo = MutableStateFlow<DoctorInfo?>(null)
    val doctorInfo: StateFlow<DoctorInfo?> = _doctorInfo

    init {
        fetchDoctorInfo()
    }

    fun fetchDoctorInfo() {
        viewModelScope.launch {
            val result = repository.getDoctorInfo()
            _doctorInfo.value = result
        }
    }
}
