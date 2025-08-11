package org.ayurtouch.project.doctor.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.ayurtouch.project.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(tittle: String) {
    TopAppBar(
        title = { tittle },
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        colors = TopAppBarDefaults.topAppBarColors(containerColor = AppColors.CreamyPeach
        )
    )
}