package org.ayurtouch.project.doctor.screens.settingScreen


sealed class SettingItem {
    data class TextItem(
        val title: String,
        val value: String,
        val moreText: String? = null
    ) : SettingItem()

    data class ChipItem(
        val title: String,
        val chips: List<String>
    ) : SettingItem()

    data class BulletItem(
        val title: String,
        val bullets: List<String>
    ) : SettingItem()

    data class ParagraphItem(
        val title: String,
        val text: String
    ) : SettingItem()
}
