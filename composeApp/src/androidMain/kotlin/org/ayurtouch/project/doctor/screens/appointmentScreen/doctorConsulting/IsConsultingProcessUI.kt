package org.ayurtouch.project.doctor.screens.appointmentScreen.doctorConsulting




import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ayurtouch.composeapp.generated.resources.Res
import ayurtouch.composeapp.generated.resources.clinical_report_ic
import ayurtouch.composeapp.generated.resources.doctor_dp
import ayurtouch.composeapp.generated.resources.email_ic
import ayurtouch.composeapp.generated.resources.emoji_ic
import ayurtouch.composeapp.generated.resources.flag_ic
import ayurtouch.composeapp.generated.resources.gender_ic
import ayurtouch.composeapp.generated.resources.phone_ic
import ayurtouch.composeapp.generated.resources.timer_ic
import ayurtouch.composeapp.generated.resources.whats_app_ic
import org.ayurtouch.project.AppColors
import org.ayurtouch.project.Monsetserrat400
import org.ayurtouch.project.Monsetserrat500
import org.ayurtouch.project.Monsetserrat600
import org.ayurtouch.project.doctor.navigationDoctorFlow.Screen
import org.ayurtouch.project.doctor.screens.appointmentScreen.SymptomsForm
import org.ayurtouch.project.doctor.screens.homeScreen.InfinityCircles
import org.ayurtouch.project.doctor.utils.CustomHeightShadowBox
import org.ayurtouch.project.doctor.utils.CustomIcon
import org.ayurtouch.project.doctor.utils.CustomImage
import org.ayurtouch.project.doctor.utils.CustomTextView
import org.ayurtouch.project.doctor.utils.CustomTittle
import org.ayurtouch.project.doctor.utils.CustomTopAppBarTitle
import org.ayurtouch.project.doctor.utils.CustomUserProfile
import org.ayurtouch.project.doctor.utils.CustomWrapHeightShadowBox
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview()
@Composable
fun  IsConsultingProcessUIPreview() {
    val navController = rememberNavController()

    IsConsultingProcessUI(
        innerPadding = PaddingValues(0.dp),
        navController = navController
    )
}

@Composable
fun IsConsultingProcessUI(innerPadding: PaddingValues, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
            .background(AppColors.White)
    ) {
        CustomTopAppBarTitle(
            title = "Appointment",
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

                    image = Res.drawable.doctor_dp,
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
                        tittle = "11:00 - 11:30 AM",
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
                    Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 10.dp)){
                        CustomTextView(
                            tittle = "Lorem ipsum, or lipsum as it is sometimes known, is dummy text used in laying out print, graphic or web designs. ",
                            fontSize = 16,
                            font = Monsetserrat400(),
                            color = AppColors.Black
                        )
                    }

                }



            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
            Column {
                CustomTittle("Clinical Reports")
                Spacer(modifier = Modifier.height(20.dp))
                CustomHeightShadowBox(
                    horizontal = 0,
                    boxHeight = 128,
                    vertical = 0,
                    backgroundColor = AppColors.OrangePrimary.copy(alpha = 0.2f),
                    cornerRadius = 10,
                    blurRadius = 20
                ) {
                    Box(modifier =
                        Modifier.fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 20.dp)){
                        CustomImage(
                            imageRes = Res.drawable.clinical_report_ic,
                            isClick = false,
                            onClick = {

                            }
                        )
                    }
                }



            }
        }
        Spacer(modifier = Modifier.height(30.dp))

        Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)){

            Column {
                CustomTittle("Analyze (or) Enter Dosha")

                Spacer(modifier = Modifier.height(30.dp))

                CustomHeightShadowBox(
                    boxHeight = 184,
                    horizontal = 0,
                    vertical = 0,
                    backgroundColor = AppColors.CreamyPeach,
                    blurRadius = 50,
                    cornerRadius = 10
                ) {
                    Column (modifier = Modifier.fillMaxSize()){
                        Box(modifier = Modifier.weight(1f)){
                            InfinityCircles()
                        }

                        Box(modifier = Modifier.weight(1f)){
                            Column {
                                OrDivider(
                                    color = AppColors.CreamyPeach,
                                    thickness = 2.dp,
                                    text = "(or)",
                                    font = Monsetserrat500(),
                                    textColor = AppColors.DarkGray,
                                    fontSize = 14
                                )



                                var text by remember { mutableStateOf("") }

                                OutlinedTextField(
                                    value = text,
                                    onValueChange = { text = it },
                                    placeholder = {
                                        Text(
                                            text = "Enter Dosha",
                                            fontFamily = Monsetserrat500(),
                                            fontSize = 14.sp,
                                            color = AppColors.MediumGray
                                        )
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 20.dp),
                                    textStyle = TextStyle(
                                        fontFamily = Monsetserrat500(),
                                        fontSize = 14.sp,
                                        color = AppColors.DarkGray
                                    ),
                                    singleLine = true,
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = AppColors.White,
                                        unfocusedBorderColor = AppColors.White
                                    )
                                )
                            }
                        }
                    }

                }
            }


        }

//        Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)){
//
//            Column {
//                 CustomTittle("Symptoms")
//
//                 Spacer(modifier = Modifier.height(10.dp))
//                 CustomHeightShadowBox(
//                     boxHeight = 60,
//                     horizontal = 0,
//                     vertical = 0,
//                     backgroundColor = AppColors.CreamyPeach,
//                     blurRadius = 20,
//                     cornerRadius = 20
//                 ) {
//
//                     var textSymptoms by remember { mutableStateOf("") }
//
//                     OutlinedTextField(
//                         value = textSymptoms,
//                         onValueChange = { textSymptoms = it },
//                         placeholder = {
//                             Text(
//                                 text = "Symptoms",
//                                 fontFamily = Monsetserrat500(),
//                                 fontSize = 14.sp,
//                                 color = AppColors.MediumGray
//                             )
//                         },
//                         modifier = Modifier
//                             .fillMaxSize(),
//                         textStyle = TextStyle(
//                             fontFamily = Monsetserrat500(),
//                             fontSize = 14.sp,
//                             color = AppColors.DarkGray
//                         ),
//                         singleLine = true,
//                         colors = OutlinedTextFieldDefaults.colors(
//                             focusedBorderColor = AppColors.White,
//                             unfocusedBorderColor = AppColors.White
//                         )
//                     )
//
//                 }
//            }
//        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Column {
//                Spacer(modifier = Modifier.height(20.dp))
//
//                CustomTittle("Symptoms")
//
//                Spacer(modifier = Modifier.height(10.dp))
//
//                // NATURE
//                OutlinedBoxField(
//                    label = "Nature"
//                )
//
//                Spacer(modifier = Modifier.height(10.dp))
//
//                // AGGRAVATING FACTOR
//                OutlinedBoxField(
//                    label = "Aggravating Factor"
//                )
//
//                Spacer(modifier = Modifier.height(10.dp))
//
//                // REDUCING FACTOR
//                OutlinedBoxField(
//                    label = "Reducing Factor"
//                )
//
//                Spacer(modifier = Modifier.height(10.dp))
//
//                // ONSET
//                OutlinedBoxField(
//                    label = "Onset"
//                )
//
//                Spacer(modifier = Modifier.height(10.dp))
//
//                // DROPDOWN 1: Symptom Status
//                CustomDropdownField(
//                    label = "Symptom Status",
//                    items = listOf("Present", "Absent", "Unknown")
//                )
//
//                Spacer(modifier = Modifier.height(10.dp))
//
//                // DROPDOWN 2: Intensity/Assessment Scale
//                CustomDropdownField(
//                    label = "Intensity/Assessment Scale",
//                    items = listOf("Mild", "Moderate", "Severe")
//                )
//
//                Spacer(modifier = Modifier.height(30.dp))
//
//                Box(modifier = Modifier
//                    .fillMaxWidth(),
//                    contentAlignment = Alignment.TopEnd
//
//                ){
//                    Row {
//
//                        CustomButtonIconAndText(
//                            buttonHeight = 30,
//                            buttonWidth = 100,
//                            buttonText = "ADD",
//                            buttonTextColor = AppColors.MediumGray,
//                            buttonTextSize = 14,
//                            buttonRadius = 20,
//                            buttonColor = AppColors.White,
//                            buttonBorderColor = AppColors.OrangePrimary,
//                            buttonIcon = Res.drawable.add_ic, // ✅ must be an Int resource ID
//                            buttonIconSize = 20,
//                            buttonIconColor = AppColors.OrangePrimary,
//                            onClick = { /* handle click */ }
//                        )
//                        Spacer(modifier = Modifier.width(20.dp))
//
//                        CustomButtonIconAndText(
//                            buttonHeight = 30,
//                            buttonWidth = 100,
//                            buttonText = "Next",
//                            buttonTextColor = AppColors.MediumGray,
//                            buttonTextSize = 14,
//                            buttonRadius = 20,
//                            buttonColor = AppColors.SoftOrange,
//                            buttonBorderColor = AppColors.SoftOrange ,
//                            buttonIcon = Res.drawable.add_ic,
//                            buttonIconSize = 20,
//                            buttonIconColor = AppColors.VibrantOrange,
//                            onClick = {   }
//                        )
//
//
//
//
//                    }
//                }
//                Spacer(modifier = Modifier.height(30.dp))
//            }

                SymptomsForm()
            }


        }
    }
}

//@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
//@Composable
//fun CustomDropdownField(
//    label: String,
//    items: List<String>
//) {
//    var expanded by remember { mutableStateOf(false) }
//    var selectedText by remember { mutableStateOf("") }
//
//    ExposedDropdownMenuBox(
//        expanded = expanded,
//        onExpandedChange = { expanded = !expanded }
//    ) {
//        OutlinedTextField(
//            value = selectedText,
//            onValueChange = {},
//            readOnly = true,
//            placeholder = {
//                Text(
//                    text = label,
//                    fontFamily = Monsetserrat500(),
//                    fontSize = 14.sp,
//                    color = AppColors.MediumGray
//                )
//            },
//            trailingIcon = {
//                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
//            },
//            modifier = Modifier
//                .fillMaxWidth(),
//            shape = RoundedCornerShape(30.dp),
//            textStyle = TextStyle(
//                fontFamily = Monsetserrat500(),
//                fontSize = 14.sp,
//                color = AppColors.DarkGray
//            ),
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedBorderColor = AppColors.CreamyPeach,
//                unfocusedBorderColor = AppColors.CreamyPeach
//            )
//        )
//
//        ExposedDropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false }
//        ) {
//            items.forEach { selectionOption ->
//                DropdownMenuItem(
//                    text = {
//                        Text(
//                            text = selectionOption,
//                            fontFamily = Monsetserrat500(),
//                            fontSize = 14.sp
//                        )
//                    },
//                    onClick = {
//                        selectedText = selectionOption
//                        expanded = false
//                    }
//                )
//            }
//        }
//    }
//}
//
//
//
//
//@Composable
//fun OutlinedBoxField(label: String) {
//    var text by remember { mutableStateOf("") }
//
//    CustomHeightShadowBox(
//        boxHeight = 60,
//        horizontal = 0,
//        vertical = 0,
//        backgroundColor = AppColors.CreamyPeach,
//        blurRadius = 20,
//        cornerRadius = 60
//    ) {
//        OutlinedTextField(
//            value = text,
//            onValueChange = { text = it },
//            placeholder = {
//                Text(
//                    text = label,
//                    fontFamily = Monsetserrat500(),
//                    fontSize = 14.sp,
//                    color = AppColors.MediumGray
//                )
//            },
//            modifier = Modifier
//                .fillMaxSize() ,
//            textStyle = TextStyle(
//                fontFamily = Monsetserrat500(),
//                fontSize = 14.sp,
//                color = AppColors.DarkGray
//            ),
//            singleLine = true,
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedBorderColor = AppColors.White,
//                unfocusedBorderColor = AppColors.White
//            )
//        )
//    }
//}
//


@Composable
fun OrDivider(
    modifier: Modifier = Modifier,
    color: Color = Color.LightGray,
    thickness: Dp = 1.dp,
    text: String = "or",
    font: FontFamily,
    textColor: Color,
    fontSize: Int,

    ) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Divider(
            color = color,
            thickness = thickness,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier=modifier.width(5.dp))
        CustomTextView(
            tittle = text,
            color = textColor,
            font = font,
            fontSize = fontSize

        )
        Spacer(modifier=modifier.width(5.dp))
        Divider(
            color = color,
            thickness = thickness,
            modifier = Modifier.weight(1f)
        )
    }
}



