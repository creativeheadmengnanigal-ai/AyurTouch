//package org.ayurtouch.project.doctor.screens.loginScreen
//
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.*
//import androidx.compose.runtime.*
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import ayurtouch.composeapp.generated.resources.*
//import org.ayurtouch.project.AppColors
//import org.ayurtouch.project.EnterYourMobileNumberTextStyle
//import org.ayurtouch.project.HelloTextStyle
//
//import org.ayurtouch.project.LoginScreenStrings
//import org.ayurtouch.project.LoginTextStyle
//import org.ayurtouch.project.Monsetserrat500
//import org.ayurtouch.project.PhoneNumberTextStyle
//import org.ayurtouch.project.PlaceHolderTextStyle
//import org.ayurtouch.project.ResendOtpTextStyle
//import org.ayurtouch.project.ToYourAccountTextStyle
//import org.ayurtouch.project.doctor.navigationDoctorFlow.Screen
//import org.jetbrains.compose.resources.painterResource
//import org.jetbrains.compose.ui.tooling.preview.Preview
//
//@Preview
//@Composable
//fun LoginScreenPreview() {
//    LoginScreen(navController = rememberNavController())
//}
//
//@Composable
//fun LoginScreen(navController: NavController) {
//    var phoneNumber by rememberSaveable  { mutableStateOf("") }
//    var isOtpSent by rememberSaveable { mutableStateOf(false) }
//    val otpStates = remember { List(4) { mutableStateOf("") } }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(AppColors.White)
//    ) {
//        Image(
//            painter = painterResource(Res.drawable.setechscope_iv),
//            contentDescription = null,
//            modifier = Modifier
//                .width(648.dp)
//                .height(690.dp)
//        )
//
//        Image(
//            painter = painterResource(Res.drawable.app_logo),
//            contentDescription = null,
//            modifier = Modifier
//                .size(120.dp)
//                .align(Alignment.TopCenter)
//                .offset(y = 57.dp)
//        )
//
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .offset(y = 212.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(
//                text = LoginScreenStrings.HELLO,
//                style = HelloTextStyle()
//            )
//            Spacer(modifier = Modifier.height(10.dp))
//            Row {
//                Text(
//                    text = LoginScreenStrings.LOGIN,
//                    style = LoginTextStyle()
//                )
//                Text(
//                    text = LoginScreenStrings.TO_YOUR_ACCOUNT,
//                    style = ToYourAccountTextStyle()
//                )
//            }
//        }
//
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .offset(y = 369.dp)
//                .padding(horizontal = 40.dp)
//        ) {
//            Text(
//                text = LoginScreenStrings.ENTER_MOBILE_NUMBER,
//                style = EnterYourMobileNumberTextStyle(),
//
//            )
//
//            PhoneNumberInputSection(
//                value = phoneNumber,
//                onValueChange = { phoneNumber = it }
//            )
//
//            Spacer(modifier = Modifier.height(40.dp))
//
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                otpStates.forEachIndexed { index, state ->
//                    OtpInputSection(
//                        value = state.value,
//                        onValueChange = { otpStates[index].value = it }
//                    )
//                }
//            }
//
//        }
//        Spacer(modifier = Modifier.height(10.dp))
//        Text(
//            modifier = Modifier.fillMaxWidth()
//                .offset(y= 540.dp)
//                .padding(horizontal = 30.dp),
//            text = LoginScreenStrings.RE_SEND_OTP,
//            textAlign = TextAlign.End,
//            style = ResendOtpTextStyle()
//
//        )
//
//        SendOtpButton(
//            text = if (!isOtpSent)LoginScreenStrings.SEND_OTP else LoginScreenStrings.LOGIN,
//            modifier = Modifier.padding(horizontal = 34.dp, vertical = 16.dp),
//            onClick = {
//                if (!isOtpSent) {
//                    phoneNumber = "9876543210"
//
//                    val otp = "9542"
//                    otp.forEachIndexed { index, char ->
//                        if (index < otpStates.size) {
//                            otpStates[index].value = char.toString()
//                        }
//                    }
//                    isOtpSent = true
//                } else {
//                    val enteredOtp = otpStates.joinToString("") { it.value }
//                    println("✅ Logging in with Phone: $phoneNumber and OTP: $enteredOtp")
//
//
//                    navController.navigate(Screen.DoctorMain.route) {
//                        popUpTo(Screen.Auth.route) { inclusive = true } // Remove Auth from backstack
//                    }
//                }
//            }
//        )
//
//
//    }
//    }
//
//
//@Composable
//fun PhoneNumberInputSection(
//    value: String,
//    onValueChange: (String) -> Unit,
//    placeholder: String = LoginScreenStrings.PHONE_NUMBER_PLACEHOLDER,
//
//) {
//    OutlinedTextField(
//        value = value,
//        onValueChange = {
//            if (it.all { ch -> ch.isDigit() }) {
//                onValueChange(it)
//            }
//        },
//        placeholder = {
//            Text(
//                text = placeholder,
//                style = PlaceHolderTextStyle()
//            )
//        },
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(50.dp)
//            .offset(y = 10.dp)
//            .background(Color.White, shape = RoundedCornerShape(23.dp)),
//        textStyle = PhoneNumberTextStyle(),
//        colors = TextFieldDefaults.outlinedTextFieldColors(
//            backgroundColor = Color.White,
//            focusedBorderColor = Color(0xFFFAC79B),
//            unfocusedBorderColor = Color(0xFFFAC79B),
//            textColor = Color.Black,
//            cursorColor = Color.Black
//        ),
//        keyboardOptions = KeyboardOptions(
//            keyboardType = KeyboardType.Phone
//        ),
//        shape = RoundedCornerShape(23.dp),
//        singleLine = true,
//        trailingIcon = {
//            Icon(
//                painter = painterResource(Res.drawable.call_ic),
//                contentDescription = "Phone Icon",
//                tint = Color.Gray
//            )
//        }
//    )
//}
//
//@Composable
//fun OtpInputSection(
//    value: String,
//    onValueChange: (String) -> Unit
//) {
//    OutlinedTextField(
//        value = value,
//        onValueChange = {
//
//            if (it.length <= 1 && it.all { ch -> ch.isDigit() }) {
//                onValueChange(it)
//            }
//        },
//        modifier = Modifier
//            .size(48.dp)
//            .background(Color.White, shape = RoundedCornerShape(10.dp)),
//        textStyle = TextStyle(
//            fontSize = 16.sp,
//            fontFamily = Monsetserrat500(),
//            color = Color.Black
//        ),
//        colors = TextFieldDefaults.outlinedTextFieldColors(
//            backgroundColor = Color.White,
//            focusedBorderColor = Color(0xFFFAC79B),
//            unfocusedBorderColor = Color(0xFFFAC79B),
//            textColor = Color.Black,
//            cursorColor = Color.Black
//        ),
//        shape = RoundedCornerShape(10.dp),
//        singleLine = true,
//        keyboardOptions = KeyboardOptions(
//            keyboardType = KeyboardType.Number
//        )
//    )
//}
//@Composable
//fun SendOtpButton(
//    text: String,
//    modifier: Modifier = Modifier,
//    onClick: () -> Unit
//) {
//    Box(
//        modifier = modifier
//            .fillMaxWidth()
//            .offset(y = 612.dp)
//            .height(50.dp)
//            .clip(RoundedCornerShape(40.dp))
//            .background(
//                brush = Brush.linearGradient(
//                    colors = listOf(Color(0xFFF68F38), Color(0xFFF44007)),
//                    start = Offset.Infinite,
//                    end = Offset.Zero
//                )
//            )
//            .clickable(onClick = onClick),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = text,
//            fontSize = 16.sp,
//            fontFamily = Monsetserrat500(),
//            color = Color.White
//        )
//    }
//
//}








package org.ayurtouch.project.doctor.screens.loginScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit
import ayurtouch.composeapp.generated.resources.*
import org.ayurtouch.project.AppColors
import org.ayurtouch.project.EnterYourMobileNumberTextStyle
import org.ayurtouch.project.HelloTextStyle
import org.ayurtouch.project.LoginScreenStrings
import org.ayurtouch.project.LoginTextStyle
import org.ayurtouch.project.Monsetserrat500
import org.ayurtouch.project.PhoneNumberTextStyle
import org.ayurtouch.project.PlaceHolderTextStyle
import org.ayurtouch.project.ResendOtpTextStyle
import org.ayurtouch.project.ToYourAccountTextStyle
import org.ayurtouch.project.doctor.navigationDoctorFlow.Screen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()
    val coroutineScope = rememberCoroutineScope()

    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var isOtpSent by rememberSaveable { mutableStateOf(false) }
    var verificationId by rememberSaveable { mutableStateOf("") }
    var resendToken by rememberSaveable { mutableStateOf<PhoneAuthProvider.ForceResendingToken?>(null) }
    var isLoading by rememberSaveable { mutableStateOf(false) }
    var errorMessage by rememberSaveable { mutableStateOf<String?>(null) }

    val otpStates = remember { List(4) { mutableStateOf("") } }

    // Callback for phone auth
    val callbacks = remember {
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // Auto-retrieval or instant verification
                credential.smsCode?.takeIf { it.length == 4 }?.let { smsCode ->
                    // Auto-fill OTP
                    smsCode.forEachIndexed { index, char ->
                        if (index < otpStates.size) {
                            otpStates[index].value = char.toString()
                        }
                    }
                }
                signInWithPhoneAuthCredential(credential, auth, firestore, phoneNumber, navController)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                errorMessage = e.message ?: "Verification failed"
                isLoading = false
            }

            override fun onCodeSent(
                id: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                verificationId = id
                resendToken = token
                isOtpSent = true
                isLoading = false
            }
        }
    }

    fun sendOtp() {
        if (phoneNumber.length != 10) {
            errorMessage = "Please enter a valid 10-digit phone number"
            return
        }

        isLoading = true
        errorMessage = null

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber("+91$phoneNumber") // Change country code if needed
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(context as android.app.Activity)
            .setCallbacks(callbacks)
            .apply {
                resendToken?.let { setForceResendingToken(it) }
            }
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun verifyOtp() {
        val otp = otpStates.joinToString("") { it.value }
        if (otp.length != 4) {
            errorMessage = "Please enter a valid 4-digit OTP"
            return
        }

        isLoading = true
        errorMessage = null

        val credential = PhoneAuthProvider.getCredential(verificationId, otp)
        signInWithPhoneAuthCredential(credential, auth, firestore, phoneNumber, navController)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.White)
    ) {
        Image(
            painter = painterResource(Res.drawable.setechscope_iv),
            contentDescription = null,
            modifier = Modifier
                .width(648.dp)
                .height(690.dp)
        )

        Image(
            painter = painterResource(Res.drawable.app_logo),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.TopCenter)
                .offset(y = 57.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 212.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = LoginScreenStrings.HELLO,
                style = HelloTextStyle()
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(
                    text = LoginScreenStrings.LOGIN,
                    style = LoginTextStyle()
                )
                Text(
                    text = LoginScreenStrings.TO_YOUR_ACCOUNT,
                    style = ToYourAccountTextStyle()
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 369.dp)
                .padding(horizontal = 40.dp)
        ) {
            Text(
                text = LoginScreenStrings.ENTER_MOBILE_NUMBER,
                style = EnterYourMobileNumberTextStyle(),
            )

            PhoneNumberInputSection(
                value = phoneNumber,
                onValueChange = { phoneNumber = it }
            )

            Spacer(modifier = Modifier.height(40.dp))

            if (isOtpSent) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    otpStates.forEachIndexed { index, state ->
                        OtpInputSection(
                            value = state.value,
                            onValueChange = {
                                if (it.length <= 1) {
                                    state.value = it
                                    // Auto move to next field
                                    if (it.isNotEmpty() && index < otpStates.size - 1) {
                                        // Focus next field
                                    }
                                }
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = LoginScreenStrings.RE_SEND_OTP,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 540.dp)
                        .padding(horizontal = 30.dp)
                        .clickable {
                            if (isOtpSent) {
                                sendOtp() // Resend OTP functionality
                            }
                        },
                    textAlign = TextAlign.End,
                    style = ResendOtpTextStyle()
                )

            }

            errorMessage?.let { message ->
                Text(
                    text = message,
                    color = Color.Red,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }

        SendOtpButton(
            text = if (!isOtpSent) LoginScreenStrings.SEND_OTP else LoginScreenStrings.LOGIN,
            modifier = Modifier.padding(horizontal = 34.dp, vertical = 16.dp),
            enabled = !isLoading,
            onClick = {
                if (!isOtpSent) {
                    sendOtp()
                } else {
                    verifyOtp()
                }
            }
        )

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
fun PhoneNumberInputSection(
    value: String,  
    onValueChange: (String) -> Unit,
    placeholder: String = LoginScreenStrings.PHONE_NUMBER_PLACEHOLDER,
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            if (it.all { ch -> ch.isDigit() }) {
                onValueChange(it)
            }
        },
        placeholder = {
            Text(
                text = placeholder,
                style = PlaceHolderTextStyle()
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .offset(y = 10.dp)
            .background(Color.White, shape = RoundedCornerShape(23.dp)),
        textStyle = PhoneNumberTextStyle(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color.White,
            focusedBorderColor = Color(0xFFFAC79B),
            unfocusedBorderColor = Color(0xFFFAC79B),
            textColor = Color.Black,
            cursorColor = Color.Black
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone
        ),
        shape = RoundedCornerShape(23.dp),
        singleLine = true,
        trailingIcon = {
            Icon(
                painter = painterResource(Res.drawable.call_ic),
                contentDescription = "Phone Icon",
                tint = Color.Gray
            )
        }
    )
}
@Composable
fun OtpInputSection(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            if (it.length <= 1 && it.all { ch -> ch.isDigit() }) {
                onValueChange(it)
            }
        },
        modifier = Modifier
            .size(48.dp)
            .background(Color.White, shape = RoundedCornerShape(10.dp)),
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontFamily = Monsetserrat500(),
            color = Color.Black,
            textAlign = TextAlign.Center
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color.White,
            focusedBorderColor = Color(0xFFFAC79B),
            unfocusedBorderColor = Color(0xFFFAC79B),
            textColor = Color.Black,
            cursorColor = Color.Black
        ),
        shape = RoundedCornerShape(10.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
    )
}
private fun signInWithPhoneAuthCredential(
    credential: PhoneAuthCredential,
    auth: FirebaseAuth,
    firestore: FirebaseFirestore,
    phoneNumber: String,
    navController: NavController
) {
    auth.signInWithCredential(credential)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // User is signed in
                val user = auth.currentUser
                user?.let {
                    // Save user data to Firestore
                    val userData = hashMapOf(
                        "phoneNumber" to phoneNumber,
                        "uid" to user.uid,
                        "createdAt" to System.currentTimeMillis()
                    )

                    firestore.collection("users").document(user.uid)
                        .set(userData)
                        .addOnSuccessListener {
                            // Navigate to main screen
                            navController.navigate(Screen.DoctorMain.route) {
                                popUpTo(Screen.Auth.route) { inclusive = true }
                            }
                        }
                        .addOnFailureListener { e ->
                            // Handle Firestore error
                        }
                }
            } else {
                // Verification failed
            }
        }
}

// Rest of your composable functions (PhoneNumberInputSection, OtpInputSection, SendOtpButton) remain the same
// Just add the enabled parameter to SendOtpButton:

@Composable
fun SendOtpButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .offset(y = 612.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(40.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFF68F38), Color(0xFFF44007)),
                    start = Offset.Infinite,
                    end = Offset.Zero
                ),
                alpha = if (enabled) 1f else 0.5f
            )
            .clickable(
                enabled = enabled,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontFamily = Monsetserrat500(),
            color = Color.White
        )
    }
}

