//package org.ayurtouch.project.doctor.screens.settingScreen
//
//import android.util.Log
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.Divider
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import ayurtouch.composeapp.generated.resources.Res
//import ayurtouch.composeapp.generated.resources.doctor_dp
//import ayurtouch.composeapp.generated.resources.down_arrow_ic
//import ayurtouch.composeapp.generated.resources.lock_ic
//import ayurtouch.composeapp.generated.resources.logout_ic
//import ayurtouch.composeapp.generated.resources.top_arrow_ic
//import org.ayurtouch.project.AppColors
//import org.ayurtouch.project.DoctorNameTextStyle
//import org.ayurtouch.project.DoctorSettingScreenString
//import org.ayurtouch.project.ExperienceTextStyle
//import org.ayurtouch.project.SettingTitleTextStyle
//import org.ayurtouch.project.SettingToggleTextStyle
//import org.ayurtouch.project.SettingValueTextStyle
//import org.ayurtouch.project.doctor.screens.settingScreen.viewmodel.DoctorSettingViewModel
//import org.ayurtouch.project.doctor.utils.CustomTopAppBar
//import org.ayurtouch.project.doctor.utils.CustomTopAppBarTitle
//import org.jetbrains.compose.resources.DrawableResource
//import org.jetbrains.compose.resources.painterResource
//import org.jetbrains.compose.ui.tooling.preview.Preview
//
//
//@Composable
//@Preview()
//fun SettingUIPreview() {
//    val navController = rememberNavController()
//    SettingUI(navController)
//}
//
//
//@Composable
//fun SettingUI(
//    navController: NavController,
//    viewModel: DoctorSettingViewModel = viewModel()
//) {
//
//
//    val doctorInfo by viewModel.doctorInfo.collectAsState()
//    LaunchedEffect(Unit) {
//        viewModel.fetchDoctorInfo()
//    }
//    Log.d("TAG", "SettingUI: $doctorInfo")
//
//
//
//
//
//
//
//
//    Scaffold(
//        topBar = { CustomTopAppBar("") },
//    ) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .verticalScroll(rememberScrollState())
//                .padding(innerPadding)
//        ) {
//            CustomTopAppBarTitle(
//                title = "Settings", onArrowClick = {
//                    navController.navigate("doctor_main")
//                    Log.d("TAG", "Appointments arrow clicked.")
//                })
//            Spacer(modifier = Modifier.height(16.dp))
//
//            doctorInfo?.let { doctor ->
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 40.dp, vertical = 10.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Image(
//                        painter = painterResource(Res.drawable.doctor_dp),
//                        contentDescription = "Profile",
//                        modifier = Modifier
//                            .size(60.dp)
//                            .clip(CircleShape),
//                        contentScale = ContentScale.Crop
//                    )
//                    Text(
//                        text = DoctorSettingScreenString.DOCTOR_NAME,
//                        style = DoctorNameTextStyle(),
//                        color = AppColors.MediumGray,
//                        modifier = Modifier.padding(top = 10.dp)
//                    )
//                    Text(
//                        text = DoctorSettingScreenString.YEAR_OF_EXPERIENCE,
//                        style = ExperienceTextStyle(),
//                        modifier = Modifier.padding(top = 10.dp)
//                    )
//                }
//                Spacer(modifier = Modifier.height(16.dp))
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 20.dp, vertical = 10.dp)
//                        .shadow(
//                            elevation = 12.dp,
//                            shape = RoundedCornerShape(8.dp),
//                            ambientColor = AppColors.OrangePrimary,
//                            spotColor = AppColors.OrangePrimary
//                        )
//                        .clip(RoundedCornerShape(8.dp))
//                        .background(AppColors.White)
//                        .padding(12.dp)
//                ) {
//
//
//                    val doctorSettingList = listOf(
//
//                        SettingItem.TextItem(
//                            title = "Languages",
//                            value = doctor.language
//                        ), SettingItem.TextItem(
//                            title = "Clinical Interests",
//                            value = doctor.clinicalInterest
//                        ), SettingItem.TextItem(
//                            title = "Location",
//                            value = doctor.location
//                        ), SettingItem.BulletItem(
//                            title = "Education",
//                            bullets =  doctor.education
//                        ), SettingItem.ChipItem(
//                            title = "Clinical Interests",
//                            chips =doctor.moreClinicalInterest
//
//                        ), SettingItem.ParagraphItem(
//                            title = "More About Physician",
//                            text = doctor.moreAboutPhysician
//                        )
//                    )
//                    DoctorSettingList(items = doctorSettingList)
//                }
//            }
//
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp, vertical = 10.dp)
//                    .shadow(
//                        elevation = 12.dp,
//                        shape = RoundedCornerShape(8.dp),
//                        ambientColor = AppColors.OrangePrimary,
//                        spotColor = AppColors.OrangePrimary
//                    )
//                    .clip(RoundedCornerShape(8.dp))
//                    .background(AppColors.White)
//                    .padding(10.dp)
//
//            ) {
//
//                Column {
//
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(44.dp)
//                            .clip(RoundedCornerShape(8.dp))
//                            .background(AppColors.White),
//                        contentAlignment = Alignment.CenterStart
//
//                    ) {
//                        CustomSettingToggle("Set Pin", Res.drawable.lock_ic)
//                    }
//
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(44.dp)
//                            .clip(RoundedCornerShape(8.dp))
//                            .background(AppColors.White),
//                        contentAlignment = Alignment.CenterStart
//
//                    ) {
//                        CustomSettingToggle("Turn Off Notification") { isOn ->
//                            Log.d("Toggle", "Notification state: $isOn")
//                        }
//                    }
//
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(44.dp)
//                            .clip(RoundedCornerShape(8.dp))
//                            .background(AppColors.White),
//                        contentAlignment = Alignment.CenterStart
//
//                    ) {
//                        Row {
//                            Box(
//                                modifier = Modifier
//                                    .size(8.dp)
//                                    .clip(CircleShape)
//                                    .background(AppColors.Green)
//                            )
//                        }
//                        CustomSettingToggle("Active", Res.drawable.down_arrow_ic)
//                    }
//
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(44.dp)
//                            .clip(RoundedCornerShape(8.dp))
//                            .background(AppColors.White),
//                        contentAlignment = Alignment.CenterStart
//
//                    ) {
//                        CustomSettingToggle("Logout", Res.drawable.logout_ic)
//
//
//                    }
//
//
//                }
//            }
//        }
//    }
//
//}
//@Composable
//fun CustomSettingToggle(tittle: String, icon: DrawableResource) {
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 20.dp),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(
//            text = tittle, style = SettingToggleTextStyle()
//        )
//        Image(
//            painter = painterResource(icon),
//            contentDescription = null,
//            modifier = Modifier.size(20.dp),
//        )
//
//    }
//
//}
//
//@Composable
//fun DoctorSettingList(items: List<SettingItem>) {
//
//    var expanded by remember { mutableStateOf(false) }
//
//    val alwaysVisibleCount = 3
//
//    val alwaysVisibleItems = items.take(alwaysVisibleCount)
//
//    val expandableItems = items.drop(alwaysVisibleCount)
//
//    Column(
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        // Always visible items
//        alwaysVisibleItems.forEach { item ->
//            when (item) {
//                is SettingItem.TextItem -> TextSettingItem(item)
//                is SettingItem.ChipItem -> ChipSettingItem(item)
//                is SettingItem.BulletItem -> BulletSettingItem(item)
//                is SettingItem.ParagraphItem -> ParagraphSettingItem(item)
//            }
//        }
//
//        // Expanded items, shown ABOVE the toggle
//        if (expanded) {
//            expandableItems.forEach { item ->
//                when (item) {
//                    is SettingItem.TextItem -> TextSettingItem(item)
//                    is SettingItem.ChipItem -> ChipSettingItem(item)
//                    is SettingItem.BulletItem -> BulletSettingItem(item)
//                    is SettingItem.ParagraphItem -> ParagraphSettingItem(item)
//                }
//            }
//        }
//
//        if (expandableItems.isNotEmpty()) {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier.clickable { expanded = !expanded }) {
//                    Text(
//                        text = if (expanded) "Less about" else "More about",
//                        style = SettingValueTextStyle().copy(color = AppColors.VibrantOrange)
//                    )
//                    Spacer(modifier = Modifier.width(6.dp))
//                    Image(
//                        painter = painterResource(
//                            if (expanded) Res.drawable.top_arrow_ic
//                            else Res.drawable.down_arrow_ic
//                        ), contentDescription = null, modifier = Modifier.size(16.dp)
//                    )
//                }
//            }
//        }
//
//    }
//}
//
//
//@Composable
//fun TextSettingItem(item: SettingItem.TextItem) {
//    Column(
//        Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//    ) {
//        Text(item.title, style = SettingTitleTextStyle())
//        Spacer(Modifier.height(4.dp))
//        Text(item.value, style = SettingValueTextStyle())
//        Divider(Modifier.padding(top = 8.dp))
//    }
//}
//
//@Composable
//fun ChipSettingItem(item: SettingItem.ChipItem) {
//    Column(
//        Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//    ) {
//        Text(item.title, style = SettingTitleTextStyle())
//        Spacer(Modifier.height(8.dp))
//        FlowRow(
//            horizontalArrangement = Arrangement.spacedBy(8.dp),
//            verticalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            item.chips.forEach { chip ->
//                Box(
//                    modifier = Modifier
//                        .background(AppColors.CreamyPeach, RoundedCornerShape(16.dp))
//                        .padding(horizontal = 12.dp, vertical = 6.dp)
//                ) {
//                    Text(
//                        text = chip, style = SettingValueTextStyle(), color = AppColors.DarkGray
//                    )
//                }
//            }
//        }
//        Divider(Modifier.padding(top = 12.dp))
//    }
//}
//
//@Composable
//fun BulletSettingItem(item: SettingItem.BulletItem) {
//    Column(
//        Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//    ) {
//        Text(item.title, style = SettingTitleTextStyle())
//        Spacer(Modifier.height(8.dp))
//        item.bullets.forEach { bullet ->
//            Row(verticalAlignment = Alignment.Top) {
//                Box(
//                    modifier = Modifier
//                        .size(8.dp)
//                        .align(Alignment.Top)
//                        .background(AppColors.VibrantOrange, shape = CircleShape)
//                )
//                Text(
//                    bullet,
//                    style = SettingValueTextStyle(),
//                    modifier = Modifier.padding(start = 10.dp)
//                )
//            }
//            Spacer(Modifier.height(4.dp))
//        }
//        Divider(Modifier.padding(top = 8.dp))
//    }
//}
//
//@Composable
//fun ParagraphSettingItem(item: SettingItem.ParagraphItem) {
//    Column(
//        Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//    ) {
//        Text(item.title, style = SettingTitleTextStyle())
//        Spacer(Modifier.height(8.dp))
//        Text(item.text, style = SettingValueTextStyle())
//        Divider(Modifier.padding(top = 8.dp))
//    }
//}
//
//
//@Composable
//fun CustomSettingToggle(
//    title: String, initialValue: Boolean = false, onToggle: (Boolean) -> Unit = {}
//) {
//    var isChecked by remember { mutableStateOf(initialValue) }
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 20.dp),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(
//            text = title, style = SettingToggleTextStyle(), color = AppColors.DarkGray
//        )
//
//        // Custom switch
//        Box(
//            modifier = Modifier
//                .width(20.dp)
//                .height(12.dp)
//                .clip(RoundedCornerShape(12.dp))
//                .offset(x = (-2).dp)
//                .border(
//                    width = 2.dp,
//                    color = if (isChecked) AppColors.Brown else AppColors.VibrantOrange,
//                    shape = RoundedCornerShape(12.dp)
//                )
//                .background(
//                    color = if (isChecked) AppColors.Brown else Color.Transparent,
//                    shape = RoundedCornerShape(12.dp)
//                )
//                .clickable {
//                    isChecked = !isChecked
//                    onToggle(isChecked)
//                }, contentAlignment = if (isChecked) Alignment.CenterEnd else Alignment.CenterStart
//        ) {
//            // Inner circle (thumb)
//            Box(
//                modifier = Modifier
//                    .size(10.dp)
//                    .clip(CircleShape)
//                    .padding(1.dp)
//                    .background(
//                        color = if (isChecked) Color.White else Color.Transparent,
//                        shape = CircleShape
//                    )
//                    .border(
//                        width = if (isChecked) 1.dp else 1.dp,
//                        color = if (isChecked) Color.Transparent else AppColors.OrangePrimary,
//                        shape = CircleShape
//                    )
//            )
//        }
//    }
//}



package org.ayurtouch.project.doctor.screens.settingScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ayurtouch.composeapp.generated.resources.Res
import ayurtouch.composeapp.generated.resources.doctor_dp
import ayurtouch.composeapp.generated.resources.down_arrow_ic
import ayurtouch.composeapp.generated.resources.lock_ic
import ayurtouch.composeapp.generated.resources.logout_ic
import ayurtouch.composeapp.generated.resources.top_arrow_ic
import org.ayurtouch.project.*
import org.ayurtouch.project.doctor.screens.homeScreen.model.DoctorInfo
import org.ayurtouch.project.doctor.screens.settingScreen.viewmodel.DoctorSettingViewModel
import org.ayurtouch.project.doctor.utils.CustomTopAppBar
import org.ayurtouch.project.doctor.utils.CustomTopAppBarTitle
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SettingUIPreview() {
    SettingUI(rememberNavController())
}

@Composable
fun SettingUI(
    navController: NavController,
    viewModel: DoctorSettingViewModel = viewModel()
) {
    val doctorInfo by viewModel.doctorInfo.collectAsState()
    LaunchedEffect(Unit) { viewModel.fetchDoctorInfo() }

    Scaffold(topBar = { CustomTopAppBar("") }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            CustomTopAppBarTitle(
                title = "Settings",
                onArrowClick = { navController.navigate("doctor_main") }
            )

            Spacer(Modifier.height(16.dp))

            doctorInfo?.let { doctor ->
                DoctorProfileSection(doctor)
                Spacer(Modifier.height(16.dp))

                SettingCard {
                    DoctorSettingList(
                        items = listOf(
                            SettingItem.TextItem("Languages", doctor.language),
                            SettingItem.TextItem("Clinical Interests", doctor.clinicalInterest),
                            SettingItem.TextItem("Location", doctor.location),
                            SettingItem.BulletItem("Education", doctor.education),
                            SettingItem.ChipItem("Clinical Interests", doctor.moreClinicalInterest),
                            SettingItem.ParagraphItem("More About Physician", doctor.moreAboutPhysician)
                        )
                    )
                }
            }

            SettingCard {
                Column{
                    Spacer(Modifier.height(16.dp))
                    CustomSettingToggle("Set Pin", Res.drawable.lock_ic)
                    Spacer(Modifier.height(16.dp))
                    CustomSettingToggle("Turn Off Notification", isSwitch = true) { isOn ->
                        Log.d("Toggle", "Notification: $isOn")
                    }
                    Spacer(Modifier.height(16.dp))
                    CustomSettingToggle("Active", Res.drawable.down_arrow_ic)
                    Spacer(Modifier.height(16.dp))
                    CustomSettingToggle("Logout", Res.drawable.logout_ic)
                    Spacer(Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
private fun DoctorProfileSection(doctor: DoctorInfo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(Res.drawable.doctor_dp),
            contentDescription = "Profile",
            modifier = Modifier.size(60.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text =  doctor.name,
            style = DoctorNameTextStyle(),
            color = AppColors.MediumGray,
            modifier = Modifier.padding(top = 10.dp)
        )
        Text(
            text = doctor.yearOfExperience,
            style = ExperienceTextStyle(),
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}

@Composable
private fun SettingCard(content: @Composable ColumnScope.() -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .shadow(12.dp, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(AppColors.White)
            .padding(12.dp)
    ) {
        Column(content = content)
    }
}

@Composable
fun DoctorSettingList(items: List<SettingItem>) {
    var expanded by remember { mutableStateOf(false) }
    val alwaysVisible = items.take(3)
    val expandable = items.drop(3)

    Column {
        alwaysVisible.forEach { RenderSettingItem(it) }
        if (expanded) expandable.forEach { RenderSettingItem(it) }

        if (expandable.isNotEmpty()) {
            MoreLessToggle(expanded) { expanded = !expanded }
        }
    }
}

@Composable
private fun RenderSettingItem(item: SettingItem) {
    when (item) {
        is SettingItem.TextItem -> TextSettingItem(item)
        is SettingItem.ChipItem -> ChipSettingItem(item)
        is SettingItem.BulletItem -> BulletSettingItem(item)
        is SettingItem.ParagraphItem -> ParagraphSettingItem(item)
    }
}

@Composable
private fun MoreLessToggle(expanded: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = if (expanded) "Less about" else "More about",
            style = SettingValueTextStyle().copy(color = AppColors.VibrantOrange)
        )
        Spacer(Modifier.width(6.dp))
        Image(
            painter = painterResource(if (expanded) Res.drawable.top_arrow_ic else Res.drawable.down_arrow_ic),
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
fun CustomSettingToggle(
    title: String,
    icon: DrawableResource? = null,
    isSwitch: Boolean = false,
    onToggle: (Boolean) -> Unit = {}
) {
    var checked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, style = SettingToggleTextStyle(), color = AppColors.DarkGray)

        when {
            icon != null -> Image(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )

            isSwitch -> CustomSwitch(checked) {
                checked = it
                onToggle(it)
            }
        }
    }
}

@Composable
private fun CustomSwitch(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Box(
        modifier = Modifier
            .width(20.dp)
            .height(12.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(2.dp, if (checked) AppColors.Brown else AppColors.VibrantOrange, RoundedCornerShape(12.dp))
            .background(if (checked) AppColors.Brown else Color.Transparent, RoundedCornerShape(12.dp))
            .clickable { onCheckedChange(!checked) },
        contentAlignment = if (checked) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(if (checked) Color.White else Color.Transparent, CircleShape)
                .border(1.dp, if (checked) Color.Transparent else AppColors.OrangePrimary, CircleShape)
        )
    }
}

/* 🔹 Setting Item Composables (missing in optimized version) */

@Composable
fun TextSettingItem(item: SettingItem.TextItem) {
    Column(Modifier.fillMaxWidth().padding(16.dp)) {
        Text(item.title, style = SettingTitleTextStyle())
        Spacer(Modifier.height(4.dp))
        Text(item.value, style = SettingValueTextStyle())
        Divider(Modifier.padding(top = 8.dp))
    }
}

@Composable
fun ChipSettingItem(item: SettingItem.ChipItem) {
    Column(Modifier.fillMaxWidth().padding(16.dp)) {
        Text(item.title, style = SettingTitleTextStyle())
        Spacer(Modifier.height(8.dp))
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            item.chips.forEach { chip ->
                Box(
                    modifier = Modifier
                        .background(AppColors.CreamyPeach, RoundedCornerShape(16.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(chip, style = SettingValueTextStyle(), color = AppColors.DarkGray)
                }
            }
        }
        Divider(Modifier.padding(top = 12.dp))
    }
}

@Composable
fun BulletSettingItem(item: SettingItem.BulletItem) {
    Column(Modifier.fillMaxWidth().padding(16.dp)) {
        Text(item.title, style = SettingTitleTextStyle())
        Spacer(Modifier.height(8.dp))
        item.bullets.forEach { bullet ->
            Row(verticalAlignment = Alignment.Top) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .align(Alignment.Top)
                        .background(AppColors.VibrantOrange, shape = CircleShape)
                )
                Text(bullet, style = SettingValueTextStyle(), modifier = Modifier.padding(start = 10.dp))
            }
            Spacer(Modifier.height(4.dp))
        }
        Divider(Modifier.padding(top = 8.dp))
    }
}

@Composable
fun ParagraphSettingItem(item: SettingItem.ParagraphItem) {
    Column(Modifier.fillMaxWidth().padding(16.dp)) {
        Text(item.title, style = SettingTitleTextStyle())
        Spacer(Modifier.height(8.dp))
        Text(item.text, style = SettingValueTextStyle())
        Divider(Modifier.padding(top = 8.dp))
    }
}
