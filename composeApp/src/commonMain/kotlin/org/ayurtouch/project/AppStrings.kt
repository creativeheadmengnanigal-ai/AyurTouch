package org.ayurtouch.project

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp


object LoginScreenStrings {
    const val HELLO = "Hello!"
    const val LOGIN = "Login"
    const val TO_YOUR_ACCOUNT = " to your account"
    const val ENTER_MOBILE_NUMBER = "Enter Your Mobile Number"
    const val PHONE_NUMBER_PLACEHOLDER = "Phone Number"
    const val RE_SEND_OTP = "Resend OTP"
    const val SEND_OTP = "Send OTP"

}

@Composable
fun HelloTextStyle(): TextStyle = TextStyle(
    color = AppColors.DarkGray,
    fontSize = 48.sp,
    fontFamily = Monsetserrat700(),
)

@Composable
fun LoginTextStyle(): TextStyle = TextStyle(
    color = AppColors.OrangePrimary,
    fontSize = 16.sp,
    fontFamily = Monsetserrat500(),
)
@Composable
fun ToYourAccountTextStyle(): TextStyle = TextStyle(
    color = AppColors.MediumGray,
    fontSize = 16.sp,
    fontFamily = Monsetserrat500(),
)

@Composable
fun EnterYourMobileNumberTextStyle(): TextStyle = TextStyle(
    color = AppColors.OrangePrimary,
    fontSize = 12.sp,
    fontFamily = Monsetserrat500(),
)

@Composable
fun PhoneNumberTextStyle(): TextStyle = TextStyle(
    color = AppColors.Black,
    fontSize = 16.sp,
    fontFamily = Monsetserrat500(),
)
@Composable
fun PlaceHolderTextStyle(): TextStyle = TextStyle(
    color = AppColors.LightGray,
    fontSize = 16.sp,
    fontFamily = Monsetserrat500(),
)


@Composable
fun ResendOtpTextStyle(): TextStyle = TextStyle(
    color = AppColors.OrangePrimary,
    fontSize = 12.sp,
    fontFamily = Monsetserrat500(),
)

//----------------------------------------------------------------------------------------------------------------------------------------

object  DoctorMainScreenString{
    const val HOME= "Home"
    const val LEAVE="Leave"
    const val PATIENT="Patient List"
    const val MEDIA="Media"
    const val MENU="Menu"

    const val SETTINGS="Settings"
    const val CHAT= "Chat"
    const val ANNOUNCEMENT="Announcements"
    const val LOGOUT="Logout"
}
@Composable
fun NavigationLabelTextStyle(): TextStyle = TextStyle(
    fontSize = 10.sp,
    fontFamily = Monsetserrat500(),
)

@Composable
fun MenuLabelTextStyle(): TextStyle = TextStyle(
    fontSize = 12.sp,
    fontFamily = Monsetserrat500(),
)

//----------------------------------------------------------------------------------------------------------------------------------------

object  DoctorHomeScreenString{

    const val WELCOME_TEXT= "Welcome"
    const val DOCTOR_NAME="Dr. Arjun Rao"
    const val STATUS="Offline Consultation"
    const val ACTIVE="Active"
    const val LOCATION="Ramanathapuram AVPRF"
    const val APPOINTMENTS="Appointments"
    const val AYURVEDIC_BODY_TYPE="Ayurvedic Body Type"
    const val SYMPTOM_ANALYSER="Symptom Analyser"
    const val SEARCH_BY="SEARCH BY"
    const val APP_TOUR="App Tour"
}
@Composable
fun WelcomeTextStyle(): TextStyle = TextStyle(
    color = AppColors.MediumGray,
    fontSize = 12.sp,
    fontFamily = Monsetserrat500(),
)

@Composable
fun DoctorNameTextStyle(): TextStyle = TextStyle(
    color = AppColors.DarkGray,
    fontSize = 16.sp,
    fontFamily = Monsetserrat600(),
)



@Composable
fun StatusTextStyle(): TextStyle = TextStyle(
    color = AppColors.MediumGray,
    fontSize = 12.sp,
    fontFamily = Monsetserrat500(),
)
@Composable
fun LocationTextStyle(): TextStyle = TextStyle(
    color = AppColors.NeutralGray,
    fontSize = 12.sp,
    fontFamily = Monsetserrat600(),
)

@Composable
fun AppointmentsTextStyle(): TextStyle = TextStyle(
    color = AppColors.Brown,
    fontSize = 16.sp,
    fontFamily = Monsetserrat600(),
)

@Composable
fun AppointmentsTimeDateTextStyle(): TextStyle = TextStyle(
    color = AppColors.White,
    fontSize = 12.sp,
    fontFamily = Monsetserrat500(),
)
@Composable
fun ConsultingTypeTextStyle(): TextStyle = TextStyle(
    color = AppColors.Black,
    fontSize = 16.sp,
    fontFamily = Monsetserrat600(),
)

@Composable
fun NewListTextStyle(): TextStyle = TextStyle(
    color = AppColors.MediumGray,
    fontSize = 12.sp,
    fontFamily = Monsetserrat500(),
)
@Composable
fun PatientNameTextStyle(): TextStyle = TextStyle(
    color = AppColors.VibrantOrange,
    fontSize = 12.sp,
    fontFamily = Monsetserrat500(),
)

@Composable
fun SearchByTextStyle(): TextStyle = TextStyle(
    color = AppColors.Brown,
    fontSize = 14.sp,
    fontFamily = Monsetserrat500(),

)
@Composable
fun SearchHereTextStyle(): TextStyle = TextStyle(
    color = AppColors.MediumGray,
    fontSize = 16.sp,
    fontFamily = Monsetserrat400(),

    )


//----------------------------------------------------------------------------------------------------------------------------------------

object  DoctorSettingScreenString{


    const val LANGUAGE="Languages"
    const val CLINICAL_INTERESTS="Clinical Interests"
    const val LOCATION="Location"
    const val EDUCATION="Education"
    const val MORE_ABOUT_PHYSICIAN="More About Physician"
    const val LESS_ABOUT_DOCTOR="Less About Doctor"
    const val SET_PIN="Set PIN"
    const val TURN_OFF_NOTIFICATION="Turn Off Notification"
    const val ACTIVE="Active"
    const val LOGOUT="Logout"
}

@Composable
fun ExperienceTextStyle(): TextStyle = TextStyle(
    color = AppColors.VibrantOrange,
    fontSize = 14.sp,
    fontFamily = Monsetserrat500(),
)

@Composable
fun SettingTitleTextStyle(): TextStyle = TextStyle(
    color = AppColors.Brown,
    fontSize = 16.sp,
    fontFamily = Jost500(),
)
@Composable
fun SettingValueTextStyle(): TextStyle = TextStyle(
    color = AppColors.MediumGray,
    fontSize = 16.sp,
    fontFamily = Jost500(),

)
@Composable
fun SettingToggleTextStyle(): TextStyle = TextStyle(
    color = AppColors.MediumGray,
    fontSize = 14.sp,
    fontFamily = Jost400(),

)

//----------------------------------------------------------------------------------------------------------------------------------------


object DoctorNotificationScreenString {

}

@Composable
fun NotificationTittleTextStyle(): TextStyle = TextStyle(
    color = AppColors.Black,
    fontSize = 12.sp,
    fontFamily = Monsetserrat600(),
)

@Composable
fun NotificationMessageTextStyle(): TextStyle = TextStyle(
    color = AppColors.Black,
    fontSize = 12.sp,
    fontFamily = Monsetserrat400(),
)

@Composable
fun NotificationTimeTextStyle(): TextStyle = TextStyle(
    color = AppColors.MediumGray,
    fontSize = 12.sp,
    fontFamily = Monsetserrat500(),
)

//----------------------------------------------------------------------------------------------------------------------------------------


