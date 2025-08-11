package org.ayurtouch.project.doctor.screens.annoncementScreen

import org.jetbrains.compose.resources.DrawableResource

data class Announcement(
    val id: Int,
    val title: String,
    val category: String,
    val description: String,
    val authorName: String,
    val authorImageRes: DrawableResource,
    val thumbnailRes: DrawableResource? = null,
    val dateTime: String,
    val isToday: Boolean,
    val section: String
)
