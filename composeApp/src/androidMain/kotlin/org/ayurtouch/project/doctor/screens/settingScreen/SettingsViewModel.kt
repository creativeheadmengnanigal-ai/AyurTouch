package org.ayurtouch.project.doctor.screens.settingScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel : ViewModel() {

    // Flag: should MenuSettingUI be shown?
    private val _isMenuSetting = MutableStateFlow(false)
    val isMenuSetting: StateFlow<Boolean> = _isMenuSetting

    fun setMenuSetting(isMenuSetting: Boolean) {
        _isMenuSetting.value = isMenuSetting
    }

    // Flag: should SettingUI be shown?
    private val _isSetting = MutableStateFlow(false)
    val isSetting: StateFlow<Boolean> = _isSetting

    fun setSetting(isSetting: Boolean) {
        _isSetting.value = isSetting
    }

    fun reset() {
        _isMenuSetting.value = false
        _isSetting.value = false
    }
}
