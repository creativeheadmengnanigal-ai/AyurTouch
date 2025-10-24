package org.ayurtouch.project.doctor.screens.appointmentScreen.cancelation



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ayurtouch.composeapp.generated.resources.*
import org.ayurtouch.project.AppColors
import org.ayurtouch.project.Monsetserrat400
import org.ayurtouch.project.Monsetserrat500
import org.ayurtouch.project.Monsetserrat600
import org.ayurtouch.project.doctor.navigationDoctorFlow.Screen
import org.ayurtouch.project.doctor.screens.appointmentScreen.reSchedule.ClinicalReportsContent
import org.ayurtouch.project.doctor.screens.appointmentScreen.reSchedule.ReasonAndSymptomContent
import org.ayurtouch.project.doctor.screens.appointmentScreen.reSchedule.ReasonCancellationContent
import org.ayurtouch.project.doctor.utils.*
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun  DoctorAppointmentCancellationPreview() {
    val navController = rememberNavController()
    DoctorAppointmentCancellationScreen(navController)
}

@Composable
fun DoctorAppointmentCancellationScreen(
    navController: NavController,

) {


    Scaffold(
        topBar = { CustomTopAppBar("") }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            CustomTopAppBarTitle(
                title = "Cancelation",
                onArrowClick = {
                    navController.navigate(
                        Screen.DoctorAppointment.route +
                                "?isAppointmentHome=true" +
                                "&isCancelled=false" +
                                "&isReschedule=false" +
                                "&isStartConsulting=false" +
                                "&isPreview=false"
                    )
                }
            )

            // Patient info box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(horizontal = 20.dp)
                    .background(
                        color = AppColors.Brown,
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(20.dp))
                    CustomUserProfile(
                        onClick = {

                        },
                        drawable = Res.drawable.doctor_dp,
                        size = 60.dp,
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        CustomTextView(
                            tittle = "Patient Name Display",
                            font = Monsetserrat600(),
                            fontSize = 14,
                            color = AppColors.White
                        )
                        Spacer(modifier = Modifier.height(4.dp))

                        CustomTextView(
                            tittle = "5022070903UWB",
                            font = Monsetserrat600(),
                            fontSize = 14,
                            color = AppColors.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Time and Paid status row
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(41.dp)
                    .padding(horizontal = 20.dp)
                    .background(
                        shape = RoundedCornerShape(10.dp),
                        color = AppColors.CreamyPeach
                    )
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomIcon(
                            imageRes = Res.drawable.timer_ic,
                            imageSize = 13,
                            isClick = true,
                            onClick = {
                                navController.navigate(Screen.DoctorSetting.route)
                            }
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        CustomTextView(
                            tittle = " 11:00 - 11:30 AM",
                            font = Monsetserrat600(),
                            fontSize = 14,
                            color = AppColors.MediumGray
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxHeight()
                            .padding(4.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(AppColors.Brown),
                        contentAlignment = Alignment.Center
                    ) {
                        CustomTextView(
                            tittle = "Paid",
                            font = Monsetserrat600(),
                            fontSize = 14,
                            color = AppColors.White
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)

            ) {

                CustomTittle(
                    tittle = "Patient Details",
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            // Patient details box
            CustomHeightShadowBox(
                boxHeight = 200,
                vertical = 20,
                horizontal = 20,
                backgroundColor = AppColors.OrangePrimary.copy(alpha = 0.2f),
                cornerRadius = 10,
                blurRadius = 50
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp)
                            ) {
                                CustomIcon(
                                    imageRes = Res.drawable.emoji_ic,
                                    imageSize = 18,
                                    isClick = false,
                                    onClick = {
                                        navController.navigate(Screen.DoctorSetting.route)
                                    }

                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                CustomTextView(
                                    tittle = "34 Years",
                                    fontSize = 14,
                                    font = Monsetserrat500(),
                                    color = AppColors.MediumGray
                                )
                            }

                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp)
                            ) {
                                CustomIcon(
                                    imageRes = Res.drawable.gender_ic,
                                    imageSize = 18,
                                    isClick = false,
                                    onClick = {
                                        navController.navigate(Screen.DoctorSetting.route)
                                    }

                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                CustomTextView(
                                    tittle = "Male",
                                    fontSize = 14,
                                    font = Monsetserrat500(),
                                    color = AppColors.MediumGray
                                )
                            }
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp)
                            ) {
                                CustomIcon(
                                    imageRes = Res.drawable.phone_ic,
                                    imageSize = 18,
                                    isClick = false,
                                    onClick = {
                                        navController.navigate(Screen.DoctorSetting.route)
                                    }

                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                CustomTextView(
                                    tittle = "0987654321",
                                    fontSize = 14,
                                    font = Monsetserrat500(),
                                    color = AppColors.MediumGray
                                )
                            }

                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp)
                            ) {
                                CustomIcon(
                                    imageRes = Res.drawable.whats_app_ic,
                                    imageSize = 18,
                                    isClick = false,
                                    onClick = {
                                        navController.navigate(Screen.DoctorSetting.route)
                                    }

                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                CustomTextView(
                                    tittle = "0987654321",
                                    fontSize = 14,
                                    font = Monsetserrat500(),
                                    color = AppColors.MediumGray
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp)
                            ) {
                                CustomIcon(
                                    imageRes = Res.drawable.email_ic,
                                    imageSize = 18,
                                    isClick = false,
                                    onClick = {
                                        navController.navigate(Screen.DoctorSetting.route)
                                    }

                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                CustomTextView(
                                    tittle = "email@gmail.com",
                                    fontSize = 14,
                                    font = Monsetserrat500(),
                                    color = AppColors.MediumGray
                                )
                            }

                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp)
                            ) {

                                CustomIcon(
                                    imageRes = Res.drawable.flag_ic,
                                    imageSize = 18,
                                    isClick = false,
                                    onClick = {
                                        navController.navigate(Screen.DoctorSetting.route)
                                    }
                                )
                                Spacer(modifier = Modifier.width(10.dp))


                                CustomTextView(
                                    tittle = "India",
                                    fontSize = 14,
                                    font = Monsetserrat500(),
                                    color = AppColors.MediumGray
                                )
                            }
                        }


                    }
                }
            }

            Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
                Column {
                    CustomTittle("Reason/ Symptom")
                    Spacer(modifier = Modifier.height(20.dp))
                    CustomWrapHeightShadowBox(
                        horizontal = 0,
                        vertical = 0,
                        backgroundColor = AppColors.OrangePrimary.copy(alpha = 0.2f),
                        cornerRadius = 10,
                        blurRadius = 20
                    ) {
                        ReasonAndSymptomContent()

                    }


                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
                Column {
                    CustomTittle("Clinical Reports")
                    Spacer(modifier = Modifier.height(20.dp))
                    CustomHeightShadowBox(
                        boxHeight = 120,
                        horizontal = 0,
                        vertical = 0,
                        backgroundColor = AppColors.OrangePrimary.copy(alpha = 0.2f),
                        cornerRadius = 10,
                        blurRadius = 20
                    ) {
                        ClinicalReportsContent()

                    }


                }
            }
            Spacer(modifier = Modifier.height(30.dp))

            Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
                Column {
                    CustomTittle("Reason for Cancelation")
                    Spacer(modifier = Modifier.height(20.dp))
                    CustomWrapHeightShadowBox(
                        horizontal = 0,
                        vertical = 0,
                        backgroundColor = AppColors.OrangePrimary.copy(alpha = 0.2f),
                        cornerRadius = 10,
                        blurRadius = 20
                    ) {
                        ReasonCancellationContent()

                    }


                }
            }

            Spacer(modifier = Modifier.height(20.dp))


            //button con
            Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Box(modifier = Modifier.weight(1f).padding(5.dp)) {

                        CustomButton(
                            buttonText = "Back",
                            buttonTextColor = AppColors.MediumGray,
                            buttonTextSize = 14,
                            buttonColor = AppColors.SoftOrange,
                            buttonRadius = 20,
                            onClick = {
                                navController.navigate(
                                    Screen.DoctorAppointment.route +
                                            "?isAppointmentHome=true" +
                                            "&isCancelled=false" +
                                            "&isReschedule=false" +
                                            "&isStartConsulting=false" +
                                            "&isPreview=false"
                                )
                            }

                        )
                    }
                    Box(modifier = Modifier.weight(1f).padding(5.dp)) {
                        CustomButton(
                            buttonText = "Cancelation",
                            buttonTextColor = AppColors.White,
                            buttonTextSize = 14,
                            buttonColor = AppColors.Brown,
                            buttonRadius = 20,
                            onClick = {
                                navController.navigate(
                                    Screen.DoctorAppointment.route +
                                            "?isAppointmentHome=false" +
                                            "&isCancelled=true" +
                                            "&isReschedule=false" +
                                            "&isStartConsulting=false" +
                                            "&isPreview=false"
                                )
                            }

                        )
                    }
                }
            }


        }
    }



    @Composable
    fun ReasonAndSymptomContent() {
        Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 10.dp)) {
            CustomTextView(
                tittle = "Lorem ipsum, or lipsum as it is sometimes known, is dummy text used in laying out print, graphic or web designs. ",
                fontSize = 16,
                font = Monsetserrat400(),
                color = AppColors.Black
            )
        }
    }


    @Composable
    fun ClinicalReportsContent() {
        Box(
            modifier =
                Modifier.fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            CustomImage(
                imageRes = Res.drawable.clinical_report_ic,
                isClick = false,
                onClick = {

                }
            )
        }
    }


    @Composable
    fun ReasonCancellationContent() {
        Box(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp, vertical = 20.dp)) {

            var reasonText by remember { mutableStateOf("") }
            OutlinedTextField(
                value = reasonText,
                onValueChange = { reasonText = it },
                placeholder = {
                    CustomTextView(
                        tittle = "Type your reason..",
                        color = AppColors.MediumGray,
                        font = Monsetserrat400(),
                        fontSize = 16
                    )
                },
                modifier = Modifier.fillMaxSize(),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = AppColors.White,
                    unfocusedContainerColor = AppColors.White,
                    cursorColor = AppColors.OrangePrimary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedLeadingIconColor = AppColors.OrangePrimary,
                    unfocusedLeadingIconColor = AppColors.OrangePrimary,
                    focusedPlaceholderColor = Color.Gray,
                    unfocusedPlaceholderColor = Color.Gray
                ),
            )
        }
    }
}

