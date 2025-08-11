package org.ayurtouch.project.doctor.screens.doctorChatScreen

import org.jetbrains.compose.resources.DrawableResource

data class ChatItem(
    val name: String,
    val role: String,
    val imageRes: DrawableResource,
    val section: String
)
