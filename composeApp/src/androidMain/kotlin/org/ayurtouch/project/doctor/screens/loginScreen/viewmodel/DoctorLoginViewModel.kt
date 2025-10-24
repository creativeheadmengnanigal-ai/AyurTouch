package org.ayurtouch.project.doctor.screens.loginScreen.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.ayurtouch.project.doctor.screens.homeScreen.model.Doctor
import org.ayurtouch.project.doctor.screens.loginScreen.model.DoctorLoginRepository
import org.ayurtouch.project.doctor.screens.loginScreen.view.DoctorLoginScreen


class DoctorLoginViewModel() : ViewModel() {

    //repository
    private val repository = DoctorLoginRepository()

    private val _loginState = MutableStateFlow<DoctorLoginState>(DoctorLoginState.Nothing)
    val loginState = _loginState.asStateFlow()

    fun saveDoctorInfo(doctor: Doctor) {
        viewModelScope.launch {
            _loginState.value = DoctorLoginState.Loading
            val result = repository.saveDoctorInfo(doctor)
            _loginState.value = if (result) DoctorLoginState.Success else DoctorLoginState.Error
        }
    }


}


sealed class DoctorLoginState {
    object Nothing : DoctorLoginState()
    object Loading : DoctorLoginState()
    object Error : DoctorLoginState()
    object Success : DoctorLoginState()

}