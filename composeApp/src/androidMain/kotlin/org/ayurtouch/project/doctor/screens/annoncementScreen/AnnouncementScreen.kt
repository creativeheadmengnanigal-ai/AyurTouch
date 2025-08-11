
package org.ayurtouch.project.doctor.screens.annoncementScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import org.jetbrains.compose.resources.painterResource
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ayurtouch.composeapp.generated.resources.Res
import ayurtouch.composeapp.generated.resources.add_ic
import ayurtouch.composeapp.generated.resources.webinor_ic
import org.ayurtouch.project.AppColors
import org.ayurtouch.project.Inter400
import org.ayurtouch.project.Jost400
import org.ayurtouch.project.Jost500
import org.ayurtouch.project.doctor.navigationDoctorFlow.Screen
import org.ayurtouch.project.doctor.utils.CustomTextView
import org.ayurtouch.project.doctor.utils.CustomTopAppBar
import org.ayurtouch.project.doctor.utils.CustomTopAppBarTitle
import org.ayurtouch.project.doctor.utils.CustomWrapHeightShadowBox
import org.ayurtouch.project.doctor.utils.UnderlinedText

@Preview
@Composable
fun AnnouncementScreenPreview() {
    val navController = rememberNavController()
    AnnouncementScreen(navController)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AnnouncementScreen(navController: NavHostController) {
    Scaffold(topBar = { CustomTopAppBar("") }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            CustomTopAppBarTitle(
                title = "Announcement",
                onArrowClick = {
                    navController.navigate(Screen.DoctorMain.route)
                }
            )

            val dummyAnnouncements = listOf(
                // Today (3 items)
                Announcement(
                    id = 1,
                    title = "Announcement Title",
                    category = "Announcement Category",
                    description = "Description about the given title or the about the event will be displayed in this section.",
                    authorName = "Author / Creator name",
                    authorImageRes = Res.drawable.add_ic,
                    thumbnailRes = Res.drawable.webinor_ic,
                    dateTime = "DD/MM/YYY - 10:00am",
                    isToday = true,
                    section = "Today"
                ),

                Announcement(
                    id = 2,
                    title = "Announcement Title",
                    category = "Announcement Category",
                    description = "Description about the given title or the about the event will be displayed in this section.",
                    authorName = "Author / Creator name",
                    authorImageRes = Res.drawable.add_ic,
                    dateTime = "DD/MM/YYY - 10:00am",
                    isToday = true,
                    section = "Today"
                ),

                Announcement(
                    id = 3,
                    title = "Announcement Title",
                    category = "Announcement Category",
                    description = "Description about the given title or the about the event will be displayed in this section.",
                    authorName = "Author / Creator name",
                    authorImageRes = Res.drawable.add_ic,
                    dateTime = "DD/MM/YYY - 10:00am",
                    isToday = true,
                    section = "Today"
                ),

                Announcement(
                    id = 4,
                    title = "Announcement Title",
                    category = "Announcement Category",
                    description = "Description about the given title or the about the event will be displayed in this section.",
                    authorName = "Author / Creator name",
                    authorImageRes = Res.drawable.add_ic,
                    dateTime = "DD/MM/YYY - 10:00am",
                    isToday = true,
                    section = "Today"
                ),

                Announcement(
                    id = 1,
                    title = "Announcement Title",
                    category = "Announcement Category",
                    description = "Description about the given title or the about the event will be displayed in this section.",
                    authorName = "Author / Creator name",
                    authorImageRes = Res.drawable.add_ic,
                    thumbnailRes = Res.drawable.webinor_ic,
                    dateTime = "DD/MM/YYY - 10:00am",
                    isToday = true,
                    section = "Today"
                ),


            )

            val groupedAnnouncements = dummyAnnouncements.groupBy { it.section }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(5.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                groupedAnnouncements.forEach { (section, items) ->
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CustomTextView(
                                tittle = section,
                                font = Inter400(),
                                color = AppColors.MediumGray,
                                fontSize = 12

                            )
                        }

                    }
                    items(items) { announcement ->
                        AnnouncementCard(
                            announcement = announcement,
                            onViewMoreClick = {
                                // Navigate or show details
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AnnouncementCard(
    announcement: Announcement,
    onViewMoreClick: () -> Unit
) {

    CustomWrapHeightShadowBox(
        horizontal = 10,
        vertical = 0,
        backgroundColor = AppColors.CreamyPeach,
        cornerRadius = 5,
        blurRadius = 50,
    ) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)) {

            // Title + "Today" badge + thumbnail
            Column(
                modifier = Modifier,

                ) {
                Column(modifier = Modifier) {
                    CustomTextView(
                        tittle = announcement.title,
                        font = Jost500(),
                        color = AppColors.VibrantOrange,
                        fontSize = 16
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    CustomTextView(
                        tittle = announcement.category,
                        font = Jost500(),
                        color = AppColors.Brown,
                        fontSize = 14
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    CustomTextView(
                        tittle = announcement.description,
                        font = Jost500(),
                        color = AppColors.Black,
                        fontSize = 14
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(announcement.authorImageRes!!),
                            contentDescription = "Author",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        CustomTextView(
                            tittle = announcement.authorName,
                            font = Jost400(),
                            color = AppColors.Brown,
                            fontSize = 14
                        )


                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    // Thumbnail Image
                    announcement.thumbnailRes?.let { thumbRes ->
                        Spacer(modifier = Modifier.height(10.dp))

                        Image(
                            painter = painterResource(thumbRes),
                            contentDescription = "Thumbnail",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(195.dp)
                                .padding(horizontal = 20.dp, vertical = 10.dp)
                                .clip(RoundedCornerShape(10.dp))
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(modifier = Modifier.fillMaxWidth()
                        .height(35.dp)
                        .clip(RoundedCornerShape(10))
                        .background(AppColors.CreamyPeach),
                        contentAlignment = Alignment.Center
                    ){
                    CustomTextView(
                        tittle = announcement.dateTime,
                        fontSize = 16,
                        font = Jost500(),
                        color = AppColors.Brown
                    )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(modifier = Modifier.fillMaxWidth()
                        .height(35.dp)
                        .clip(RoundedCornerShape(20)),
                        contentAlignment = Alignment.Center
                    ){
                        UnderlinedText(
                            text =  "View More Details" ,
                            fontSize = 16,
                            font = Jost500(),
                            color = AppColors.Blue
                        )
                    }

                }

                Spacer(modifier = Modifier.height(12.dp))


            }
        }
    }
}



