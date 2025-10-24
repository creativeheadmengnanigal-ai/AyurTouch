package org.ayurtouch.project.doctor.screens.homeScreen.view

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ayurtouch.composeapp.generated.resources.*
import com.google.firebase.auth.FirebaseAuth
import org.ayurtouch.project.*
import org.ayurtouch.project.doctor.navigationDoctorFlow.Screen
import org.ayurtouch.project.doctor.screens.homeScreen.model.Appointment
import org.ayurtouch.project.doctor.screens.homeScreen.viewmodel.DoctorHomeScreenStates
import org.ayurtouch.project.doctor.screens.homeScreen.viewmodel.DoctorHomeScreenViewModel
import org.ayurtouch.project.doctor.utils.CustomHeightShadowBox

import org.ayurtouch.project.doctor.utils.CustomUserProfile
import org.ayurtouch.project.doctor.utils.CustomWrapImage
import org.ayurtouch.project.doctor.utils.CustomWrapShadowBox
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


private const val TAG = "HomeScreen"

@Preview
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {


    var isSecondBoxVisible by remember { mutableStateOf(true) }
    var isStatusActive by remember { mutableStateOf(false) }
    var selectedTab by remember { mutableStateOf("Symptoms") }
    var searchQuery by remember { mutableStateOf("") }

    val appointments = listOf(
        Appointment("11:00 AM", "08 Sep", "Video Consulting", "Patient Name", "New Visit"),
        Appointment("02:30 PM", "08 Sep", "Audio Consulting", "Patient Name", "Follow-up"),
        Appointment("04:00 PM", "08 Sep", "Video Consulting", "Patient Name", "New Visit")
    )

    val viewModel: DoctorHomeScreenViewModel = viewModel()
    val state by viewModel.doctorHomeScreenStates.collectAsState()
    val currentUid= FirebaseAuth.getInstance().currentUser?.uid.toString()

    //
    LaunchedEffect(Unit) {
        viewModel.fetchDoctorInfo(currentUid)
    }

    when (state) {
        is DoctorHomeScreenStates.Nothing -> Text("No data yet")
        is DoctorHomeScreenStates.Loading -> CircularProgressIndicator()
        is DoctorHomeScreenStates.Success -> {
            val doctor = (state as DoctorHomeScreenStates.Success).doctor



            }

        else -> {}
    }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.White)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 10.dp)
    ) {
        TopBar(navController,state)

        StatusLocationSection(
            isStatusActive = isStatusActive,
            isSecondBoxVisible = isSecondBoxVisible,
            onStatusClick = {
                isStatusActive = !isStatusActive
                isSecondBoxVisible = !isSecondBoxVisible
                Log.d(TAG, "Status toggled. Active: $isStatusActive")
            },
            onLocationClick = {
                isSecondBoxVisible = false
                isStatusActive = true
                Log.d(TAG, "Location box clicked.")
            })

        TitleWithArrowRow(
            title = DoctorHomeScreenString.APPOINTMENTS, isArrowVisible = true
        ) {
        }

        AppointmentList(appointments, navController)

        TitleWithArrowRow(title = DoctorHomeScreenString.AYURVEDIC_BODY_TYPE)

        AyurvedicBox()

        TitleWithArrowRow(title = DoctorHomeScreenString.SYMPTOM_ANALYSER)

        SectionTitle(DoctorHomeScreenString.SEARCH_BY)

        SymptomDiseaseSwitch(
            selectedOption = selectedTab, onOptionSelected = { selectedTab = it })

        SearchBar(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            startIcon = painterResource(Res.drawable.search_ic)
        )

        TitleWithArrowRow(title = DoctorHomeScreenString.APP_TOUR)

        AppTourContainer()


        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = SearchByTextStyle(),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    )
}

@Composable
fun TopBar(navController: NavController, state: DoctorHomeScreenStates) {



    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        when(state){


            is DoctorHomeScreenStates.Success -> {
                val doctor = (state as DoctorHomeScreenStates.Success).doctor
                CustomUserProfile(
                    onClick = {
                        navController.navigate(
                            Screen.DoctorSetting.route +
                                    "?isSetting=true" +
                                    "&isMenuSetting=false"

                        )
                    },


                    size = 60.dp,
                    imageUrl = doctor.profileImage,
                )
            }
            else -> {}
        }





        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp)
        ) {
            Text(
                text = DoctorHomeScreenString.WELCOME_TEXT,
                style = WelcomeTextStyle(),
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                text = DoctorHomeScreenString.DOCTOR_NAME,
                style = DoctorNameTextStyle(),
                modifier = Modifier.padding(top = 5.dp)
            )
        }

        IconButton(
            onClick = { navController.navigate("notification_screen") },
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFFFFE8CC), CircleShape)
        ) {
            Icon(
                painter = painterResource(Res.drawable.bell_ic),
                contentDescription = "Notifications",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}


@Composable
fun TitleWithArrowRow(
    title: String, isArrowVisible: Boolean = false, onArrowClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title, style = AppointmentsTextStyle(), modifier = Modifier.weight(1f)
        )
        if (isArrowVisible) {
            Icon(
                painter = painterResource(Res.drawable.arrow_ic),
                contentDescription = "Arrow Icon",
                tint = AppColors.VibrantOrange,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onArrowClick?.invoke() })
        }
    }
}


@Composable
fun AppointmentList(appointments: List<Appointment>, navController: NavController) {
    LazyRow(
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(appointments) { index, appointment ->
            AppointmentCard(appointment, index, navController)
        }
    }
}

@Composable
fun AppointmentCard(appointment: Appointment, index: Int, navController: NavController) {
    val backgroundImage = if (index % 2 == 0) {
        Res.drawable.box_time_date_ic
    } else {
        Res.drawable.box_time_date_2_ic
    }

    Column(
        modifier = Modifier.padding(start = 5.dp),
        horizontalAlignment = if (index % 2 == 0) Alignment.Start else Alignment.End
    ) {
        if (index % 2 == 0) {
            TimeDateBox(appointment.time, appointment.date, backgroundImage, isOddIndex = true)
            Spacer(Modifier.height(8.dp))
            AppointmentDetailsBox(appointment, navController)
        } else {
            AppointmentDetailsBox(appointment, navController)
            Spacer(Modifier.height(8.dp))
            TimeDateBox(appointment.time, appointment.date, backgroundImage, isOddIndex = false)
        }
    }
}


@Composable
fun TimeDateBox(
    time: String, date: String, backgroundImageRes: DrawableResource, isOddIndex: Boolean
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(width = 143.dp, height = 83.dp)
    ) {
        CustomWrapImage(
            imageRes = backgroundImageRes, height = 83, width = 100, isClick = false, onClick = {})
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(
                top = if (!isOddIndex) 10.dp else 0.dp,
                bottom = if (isOddIndex) 10.dp else 0.dp,

                ),

            ) {
            Text(time, style = AppointmentsTimeDateTextStyle())
            Text(date, style = AppointmentsTimeDateTextStyle())
        }
    }
}


@Composable
fun AppointmentDetailsBox(appointment: Appointment, navController: NavController) {
    Box(
        modifier = Modifier

            .clickable {
                navController.navigate(
                    Screen.DoctorAppointment.route + "?isAppointmentHome=true" + "&isCancelled=false" + "&isReschedule=false" + "&isStartConsulting=false" + "&isPreview=false"
                )
                Log.d(TAG, "Appointment clicked: $appointment")
            }) {

        CustomWrapShadowBox(
            boxHeight = 143,
            boxWidth = 143,
            vertical = 0,
            horizontal = 0,
            backgroundColor = AppColors.CreamyPeach,
            cornerRadius = 10,
            blurRadius = 100
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Text(appointment.consultingType, style = ConsultingTypeTextStyle())
                Spacer(Modifier.height(20.dp))
                Text(appointment.status, style = PatientNameTextStyle())
                Spacer(Modifier.height(10.dp))
                Text(appointment.patientName, style = NewListTextStyle())
            }
        }

    }
}

@Composable
fun AyurvedicBox() {

    CustomHeightShadowBox(
        boxHeight = 94,
        horizontal = 20,
        vertical = 0,
        backgroundColor = AppColors.CreamyPeach,
        blurRadius = 50,
        cornerRadius = 10
    ) {
        InfinityCircles()
    }


}

@Composable
fun InfinityCircles() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxSize()
    ) {
        listOf(
            Res.drawable.yoga_type_1_ic to 20.dp,
            Res.drawable.yoga_type_2_ic to 10.dp,
            Res.drawable.yoga_type_3_ic to 1.dp
        ).forEachIndexed { index, (res, offset) ->
            Image(
                painter = painterResource(res),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .offset(x = offset)
                    .clip(CircleShape)
                    .zIndex((3 - index).toFloat())
            )
        }

        Box(
            modifier = Modifier
                .width(193.dp)
                .height(40.dp)
                .offset(x = (-30).dp)
                .clip(RoundedCornerShape(20.dp))
                .background(AppColors.Brown),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Analyze your Dosha", style = PatientNameTextStyle(), color = Color.White
            )
        }
    }
}

@Composable
fun SymptomDiseaseSwitch(
    selectedOption: String, onOptionSelected: (String) -> Unit
) {
    val options = listOf("Symptoms", "Disease")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 70.dp, vertical = 20.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(AppColors.CreamyPeach)
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        options.forEach { option ->
            val isSelected = option == selectedOption
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(if (isSelected) AppColors.White else Color.Transparent)
                    .clickable { onOptionSelected(option) }
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center) {
                Text(
                    text = option,

                    color = if (isSelected) Color.Black else AppColors.OrangePrimary,
                    fontFamily = Monsetserrat400(),

                    )
            }
        }
    }
}

@Composable
fun SearchBar(
    value: String, onValueChange: (String) -> Unit, hint: String = "Search here", startIcon: Painter
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    AppColors.OrangePrimary.copy(alpha = 0.4f), RoundedCornerShape(35.dp)
                )
                .blur(20.dp)
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(1.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(AppColors.White)
        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                leadingIcon = {
                    Icon(
                        painter = startIcon,
                        contentDescription = "Search Icon",
                        tint = AppColors.MediumGray
                    )
                },
                placeholder = {
                    Text(
                        text = hint, style = SearchHereTextStyle(), color = Color.Gray
                    )
                },
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
                shape = RoundedCornerShape(30.dp),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )
        }
    }
}

@Composable
fun AppTourContainer() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(122.dp)
            .padding(horizontal = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    AppColors.OrangePrimary.copy(alpha = 0.4f), RoundedCornerShape(10.dp)
                )
                .blur(20.dp)
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(1.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(AppColors.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "App Tutorial", style = ConsultingTypeTextStyle()
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = "Unlock the full potential of our app with just one click – watch our in-depth video.",
                        style = PatientNameTextStyle(),
                        color = AppColors.MediumGray
                    )
                }
                Image(
                    painter = painterResource(Res.drawable.app_tour_ic),
                    contentDescription = "App Tour",
                    modifier = Modifier
                        .width(41.dp)
                        .height(93.dp)
                        .padding(10.dp)
                )
            }
        }
    }
}

@Composable
fun StatusLocationSection(
    isStatusActive: Boolean,
    isSecondBoxVisible: Boolean,
    onStatusClick: () -> Unit,
    onLocationClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Main status box (Active / Inactive)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(AppColors.CreamyPeach)
                .clickable { onStatusClick() },
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(if (isStatusActive) AppColors.Green else AppColors.Red)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = if (isStatusActive) DoctorHomeScreenString.ACTIVE
                    else DoctorHomeScreenString.STATUS, style = StatusTextStyle()
                )
            }
        }

        // Secondary location box (visible only when inactive)
        if (isSecondBoxVisible) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(AppColors.CreamyPeach)
                    .clickable { onLocationClick() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = DoctorHomeScreenString.LOCATION, style = LocationTextStyle()
                )
            }
        }
    }
}
