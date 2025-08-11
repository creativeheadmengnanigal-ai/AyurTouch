package org.ayurtouch.project.doctor.screens.notificationScreen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.ayurtouch.project.NotificationMessageTextStyle
import org.ayurtouch.project.NotificationTimeTextStyle
import org.ayurtouch.project.NotificationTittleTextStyle
import org.ayurtouch.project.doctor.utils.CustomTopAppBar
import org.ayurtouch.project.doctor.utils.CustomTopAppBarTitle
import org.jetbrains.compose.ui.tooling.preview.Preview


// ------------------------------------------
// Preview
// ------------------------------------------

@Preview()
@Composable
fun NotificationScreenScreenPreview() {
    val navController = rememberNavController()
    NotificationScreen(navController = navController)
}


// ------------------------------------------
// Notification
// ------------------------------------------

@Composable
fun NotificationScreen(navController: NavController) {

    val sampleNotifications = listOf(
        NotificationItem(
            title = "Announcement",
            message = "Lorem ipsum, or lipsum as it is sometimes known, is dummy text",
            time = "Today at 2:20pm"
        ),
        NotificationItem(
            title = "Announcement",
            message = "Lorem ipsum, or lipsum as it is sometimes known, is dummy text",
            time = "Yesterday at 4:10pm"
        ),
        NotificationItem(
            title = "Announcement",
            message = "Lorem ipsum, or lipsum as it is sometimes known, is dummy text",
            time = "Yesterday at 4:10pm"
        )
    )

    Scaffold(
        topBar = {
            CustomTopAppBar("")
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            CustomTopAppBarTitle(
                title = "Notification",
                onArrowClick = {
                    navController.navigate("doctor_main")
                    Log.d("TAG", "Back arrow clicked.")
                }
            )

            NotificationList(notifications = sampleNotifications)
        }
    }
}

// ------------------------------------------
// Notification List and Item UI
// ------------------------------------------

@Composable
fun NotificationList(notifications: List<NotificationItem>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(notifications) { item ->
            NotificationListItem(item)
        }
    }
}

@Composable
fun NotificationListItem(item: NotificationItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = item.title,
            style = NotificationTittleTextStyle(),
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = item.message,
            style =  NotificationMessageTextStyle()
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = item.time,
            style =NotificationTimeTextStyle(),

        )
        Divider(modifier = Modifier.padding(top = 12.dp))
    }
}

