package org.ayurtouch.project.doctor.screens.doctorChatScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ayurtouch.composeapp.generated.resources.Res
import ayurtouch.composeapp.generated.resources.add_ic
import ayurtouch.composeapp.generated.resources.left_arrow_ic
import ayurtouch.composeapp.generated.resources.mic_ic
import ayurtouch.composeapp.generated.resources.send_ic
import ayurtouch.composeapp.generated.resources.user_profile
import org.ayurtouch.project.AppColors
import org.ayurtouch.project.DMSans400
import org.ayurtouch.project.DMSans500
import org.ayurtouch.project.DMSans700
import org.ayurtouch.project.Monsetserrat400
import org.ayurtouch.project.doctor.navigationDoctorFlow.Screen
import org.ayurtouch.project.doctor.utils.CustomCircleIcon
import org.ayurtouch.project.doctor.utils.CustomTextView
import org.ayurtouch.project.doctor.utils.CustomTopAppBar
import org.jetbrains.compose.resources.painterResource

data class ChatMessage(
    val message: String, val isDoctor: Boolean, val isPatient: Boolean, val dateAndTime: String
)


@Preview(showBackground = true)
@Composable
fun ChatDetailScreenPreview() {
    val navController = rememberNavController()
    ChatDetailScreen(
        name = "Dr. Ajay Kumar", role = "Cardiologist", navController = navController
    )
}


@Composable
fun ChatDetailScreen(
    name: String, role: String, navController: NavHostController
) {
    val chatMessages = remember {
        listOf(
            ChatMessage("Reviewed the patient I referred?", true, false, "Wed 8:21 AM"),
            ChatMessage("Yes, mild infection. Prescribed antibiotics.", false, true, "Wed 8:21 AM"),
            ChatMessage("Keep me updated.", true, false, "Wed 8:21 AM"),
            ChatMessage("Will do.", false, true, "Wed 8:21 AM")
        )
    }

    var messageText by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(topBar = {
        Column(Modifier.background(AppColors.White)) {
            CustomTopAppBar("")
            ChatHeader(name, role) {
                navController.navigate(Screen.DoctorChat.route)
            }
        }

    }, bottomBar = {
        ChatInputBar(
            messageText = messageText,
            onMessageChange = { messageText = it },
            onSend = { })
    }) { innerPadding ->
        val groupedMessages = chatMessages.sortedBy { it.dateAndTime }.groupBy { it.dateAndTime }

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(AppColors.White)
                .padding(horizontal = 20.dp),

            verticalArrangement = Arrangement.Bottom,
            reverseLayout = true
        ) {
            groupedMessages.toList().reversed().forEach { (date, messages) ->
                items(messages.reversed()) { chat ->
                    ChatBubble(chat)
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item {
                    DateDivider(date)
                }
            }
        }
    }
}
@Composable
fun ChatBubble(chat: ChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (chat.isDoctor) Arrangement.Start else Arrangement.End
    ) {
        // Profile for doctor (left side)
        if (chat.isDoctor) {
            CustomCircleIcon(
                size = 32.dp,
                backgroundColor = AppColors.CreamyPeach,
                icon = painterResource(Res.drawable.user_profile),
                iconTintColor = AppColors.Brown,
                iconSize = 18.dp,
                shadowColor = AppColors.Transparent
            )
            Spacer(modifier = Modifier.width(6.dp))
        }

        val bubbleShape = if (chat.isDoctor) {
            RoundedCornerShape(
                topStart = 0.dp, topEnd = 24.dp, bottomEnd = 24.dp, bottomStart = 24.dp
            )
        } else {
            RoundedCornerShape(24.dp) // patient: all sides same
        }

        val bubbleColor = if (chat.isDoctor) AppColors.LightGrayishWhite else AppColors.CreamyPeach
        val textColor = if (chat.isDoctor) AppColors.Black else AppColors.Brown

        Column(
            modifier = Modifier
                .background(
                    color = bubbleColor, shape = bubbleShape
                )
                .padding(20.dp)
                .widthIn(max = 250.dp)
        ) {
            CustomTextView(
                tittle = chat.message, fontSize = 14, font = Monsetserrat400(), color = textColor
            )
        }

    }
}


@Composable
fun ChatHeader(name: String, role: String, onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppColors.White)
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomCircleIcon(
            size = 40.dp,
            backgroundColor = AppColors.Transparent,
            icon = painterResource(Res.drawable.left_arrow_ic),
            iconTintColor = AppColors.VibrantOrange,
            shadowColor = Color.Transparent,
            onClick = onBackClick,
            iconSize = 28.dp
        )
        Spacer(modifier = Modifier.width(12.dp))
        CustomCircleIcon(
            size = 40.dp,
            backgroundColor = AppColors.CreamyPeach,
            icon = painterResource(Res.drawable.user_profile),
            iconTintColor = AppColors.Brown,
            iconSize = 24.dp,
            shadowColor = AppColors.Transparent
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            CustomTextView(name, 14, DMSans700(), AppColors.Black)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(AppColors.Green, CircleShape)
                )
                Spacer(modifier = Modifier.width(5.dp))
                CustomTextView("active", 12, DMSans500(), AppColors.LightGray)
            }
        }
    }
}

//@Composable
//fun ChatInputBar(
//    messageText: TextFieldValue, onMessageChange: (TextFieldValue) -> Unit, onSend: () -> Unit
//) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//
//            .background(AppColors.White)
//            .padding(horizontal = 20.dp)
//             ,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        TextField(
//            value = messageText,
//            onValueChange = onMessageChange,
//            modifier = Modifier
//                .weight(1f)
//
//                .border(1.dp, Color.LightGray, RoundedCornerShape(25.dp))
//                .clip(RoundedCornerShape(25.dp)),
//            placeholder = {
//                CustomTextView("Type a message...", 16, DMSans400(), AppColors.LightGray)
//            },
//            trailingIcon = {
//                Icon(
//                    painter = painterResource(Res.drawable.mic_ic),
//                    contentDescription = "Voice",
//                    modifier = Modifier
//                        .size(24.dp)
//                        .clickable { /* voice */ })
//            },
//            colors = TextFieldDefaults.colors(
//                focusedContainerColor = AppColors.White,
//                unfocusedContainerColor = AppColors.White,
//                focusedTextColor = AppColors.Black,
//                unfocusedTextColor = AppColors.Black,
//                cursorColor = AppColors.Green
//            ),
//            maxLines = 3
//        )
//        Spacer(modifier = Modifier.width(8.dp))
//        Icon(
//            painter = painterResource(Res.drawable.send_ic),
//            contentDescription = "Send",
//            modifier = Modifier
//                .size(40.dp)
//                .clip(CircleShape)
//                .background(AppColors.Brown)
//                .padding(8.dp)
//                .clickable { onSend() },
//            tint = AppColors.White
//        )
//    }
//}
@Composable
fun ChatInputBar(
    messageText: TextFieldValue,
    onMessageChange: (TextFieldValue) -> Unit,
    onSend: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppColors.White)
            .padding(horizontal = 20.dp, vertical = 8.dp)

        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = messageText,
            onValueChange = onMessageChange,
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(
                color = AppColors.Black,
                fontSize = 16.sp
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .height(45.dp)
                        .fillMaxWidth()
                        .border(1.dp, Color.LightGray, RoundedCornerShape(25.dp))
                        .clip(RoundedCornerShape(25.dp))
                        .background(AppColors.White)
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        if (messageText.text.isEmpty()) {
                            CustomTextView("Type a message...", 16, DMSans400(), AppColors.LightGray)
                        }
                        innerTextField()
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(Res.drawable.mic_ic),
                        contentDescription = "Voice",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { /* voice action */ },
                        tint = AppColors.LightGray
                    )
                }
            },
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Icon(
            painter = painterResource(Res.drawable.send_ic),
            contentDescription = "Send",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(AppColors.Brown)
                .padding(8.dp)
                .clickable { onSend() },
            tint = AppColors.White

        )
    }
}





@Composable
fun DateDivider(date: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        CustomTextView(date, 12, DMSans500(), AppColors.LightGray)
    }
}
