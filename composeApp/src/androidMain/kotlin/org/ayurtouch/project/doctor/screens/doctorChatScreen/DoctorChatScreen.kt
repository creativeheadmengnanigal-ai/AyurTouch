

package org.ayurtouch.project.doctor.screens.doctorChatScreen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ayurtouch.composeapp.generated.resources.Res
import ayurtouch.composeapp.generated.resources.add_ic
import ayurtouch.composeapp.generated.resources.doctor_dp
import ayurtouch.composeapp.generated.resources.filter_ic
import ayurtouch.composeapp.generated.resources.left_arrow_ic
import ayurtouch.composeapp.generated.resources.search_ic
import org.ayurtouch.project.AppColors
import org.ayurtouch.project.Inter400
import org.ayurtouch.project.Monsetserrat600
import org.ayurtouch.project.doctor.navigationDoctorFlow.Screen
import org.ayurtouch.project.doctor.utils.*

import org.jetbrains.compose.resources.painterResource
import java.net.URLEncoder

@Preview
@Composable
fun DoctorChatScreenPreview() {
    val navController = rememberNavController()
    DoctorChatScreen(navController)
}

@Composable
fun DoctorChatScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CustomTopAppBar("")
        },
        containerColor = AppColors.White
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Floating Add Button
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .zIndex(1f)
                    .offset(y = -(50.dp))
                    .padding(end = 20.dp)
            ) {
                CustomCircleIcon(
                    size = 50.dp,
                    backgroundColor = AppColors.Brown,
                    icon = painterResource(Res.drawable.add_ic),
                    iconTintColor = AppColors.White,
                    shadowColor = Color.Gray,
                    onClick = {
                        navController.popBackStack()
                    },
                    iconSize = 24.dp
                )
            }

            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(bottom = 80.dp)
            ) {
                CustomTopAppBarTitle(
                    title = "Chat",
                    onArrowClick = {
                        navController.navigate(Screen.DoctorMain.route)
                    },

                )
                // Search & Filter
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1.7f)
                    ) {
                        var text by remember { mutableStateOf("") }

                        CustomOutlinedTextField(
                            value = text,
                            onValueChange = { text = it },
                            height = 40.dp,
                            cornerRadius = 30.dp,
                            backgroundColor = Color.White,
                            hintText = "Search",
                            hintTextColor = Color.Gray,
                            endIcon = painterResource(Res.drawable.search_ic),
                            shadowColor = Color.Gray
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(0.3f),
                        contentAlignment = Alignment.Center
                    ) {
                        CustomCircleIcon(
                            size = 40.dp,
                            backgroundColor = AppColors.White,
                            icon = painterResource(Res.drawable.filter_ic),
                            onClick = { },
                            iconTintColor = AppColors.Black,
                            iconSize = 16.dp
                        )
                    }
                }

                // Dummy Chat List
                val dummyChats = listOf(
                    ChatItem("Dr. John Smith", "Doctor", Res.drawable.doctor_dp, "Today"),
                    ChatItem("Emily Davis", "Patient", Res.drawable.doctor_dp, "Today"),
                    ChatItem("Dr. Robert Brown", "Doctor", Res.drawable.doctor_dp, "Yesterday"),
                    ChatItem("Sarah Wilson", "Patient", Res.drawable.doctor_dp, "Yesterday"),
                    ChatItem("Dr. Michael Lee", "Doctor", Res.drawable.doctor_dp, "Last Week"),
                    ChatItem("Olivia Taylor", "Patient", Res.drawable.doctor_dp, "Last Week")
                )

                val groupedChats = dummyChats.groupBy { it.section }

                groupedChats.forEach { (section, items) ->
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CustomTextView(
                            tittle = section,
                            fontSize = 12,
                            color = AppColors.LightGray,
                            font = Inter400()
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    items.forEach { chat ->
                        ChatListItem(chat = chat) {
                            val encodedName = Uri.encode(chat.name)
                            val encodedRole = Uri.encode(chat.role)

                            navController.navigate("chat_detail_screen/$encodedName/$encodedRole")
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ChatListItem(chat: ChatItem, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(AppColors.CreamyPeach, shape = RoundedCornerShape(10.dp))
            .padding(12.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomUserProfile(
            size = 48.dp,
            drawable = chat.imageRes,
            onClick = { }
        )

        Column(modifier = Modifier.padding(start = 12.dp)) {
            CustomTextView(
                tittle = chat.name,
                fontSize = 14,
                color = AppColors.Black,
                font = Monsetserrat600()
            )
            Spacer(modifier = Modifier.height(10.dp))
            CustomTextView(
                tittle = chat.role,
                fontSize = 12,
                color = AppColors.VibrantOrange,
                font = Monsetserrat600()
            )
        }
    }
}

