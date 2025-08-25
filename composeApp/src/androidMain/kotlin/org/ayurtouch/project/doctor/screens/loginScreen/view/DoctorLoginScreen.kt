package org.ayurtouch.project.doctor.screens.loginScreen.view


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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ayurtouch.composeapp.generated.resources.*
import com.google.firebase.FirebaseException
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import org.ayurtouch.project.AppColors
import org.ayurtouch.project.EnterYourMobileNumberTextStyle
import org.ayurtouch.project.HelloTextStyle
import org.ayurtouch.project.LoginScreenStrings
import org.ayurtouch.project.LoginTextStyle
import org.ayurtouch.project.MainActivity
import org.ayurtouch.project.Monsetserrat500
import org.ayurtouch.project.PhoneNumberTextStyle
import org.ayurtouch.project.PlaceHolderTextStyle
import org.ayurtouch.project.ResendOtpTextStyle
import org.ayurtouch.project.ToYourAccountTextStyle
import org.ayurtouch.project.doctor.navigationDoctorFlow.Screen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import java.util.concurrent.TimeUnit

@Preview()
@Composable
fun DoctorLoginScreen(
    navController: NavController,
    activity: MainActivity,
    auth: FirebaseAuth,
    ) {
    var phoneNumber by rememberSaveable  { mutableStateOf("") }
    var isOtpSent by rememberSaveable { mutableStateOf(false) }
    val otpStates = remember { List(6) { mutableStateOf("") } }
    var storedVerificationId by rememberSaveable { mutableStateOf<String?>(null) }


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


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                otpStates.forEachIndexed { index, state ->
                    OtpInputSection(
                        value = state.value,
                        onValueChange = { otpStates[index].value = it }
                    )
                }
            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier.fillMaxWidth()
                .offset(y= 540.dp)
                .padding(horizontal = 30.dp),
            text = LoginScreenStrings.RE_SEND_OTP,
            textAlign = TextAlign.End,
            style = ResendOtpTextStyle()

        )


//        SendOtpButton(
//            text = if (!isOtpSent) LoginScreenStrings.SEND_OTP else LoginScreenStrings.LOGIN,
//            modifier = Modifier.padding(horizontal = 34.dp, vertical = 16.dp),
//            onClick = {
//                if (!isOtpSent) {
//                    // ✅ Send OTP
//                    if (phoneNumber.length < 10) {
//                        println("❌ Invalid phone number")
//                    } else {
//                        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                             override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//                                // Auto-fill OTP if Google Play services catches it
//                                credential.smsCode?.let { code ->
//                                    otpStates.forEachIndexed { index, state ->
//                                        if (index < code.length) state.value = code[index].toString()
//                                    }
//                                }
//                            }
//
//                            override fun onVerificationFailed(e: FirebaseException) {
//                                println("❌ OTP Send Failed: ${e.message}")
//                            }
//
//                            override fun onCodeSent(
//                                verificationId: String,
//                                token: PhoneAuthProvider.ForceResendingToken
//                            ) {
//                                println("✅ OTP Sent to $phoneNumber")
//                                isOtpSent = true
//                                var storedVerificationId = verificationId
//                            }
//                        }
//
//                        val options = PhoneAuthOptions.newBuilder(auth)
//                            .setPhoneNumber("+91$phoneNumber") // add country code
//                            .setTimeout(60L, TimeUnit.SECONDS)
//                            .setActivity(activity)
//                            .setCallbacks(callbacks)
//                            .build()
//
//                        PhoneAuthProvider.verifyPhoneNumber(options)
//                    }
//                } else {
//                    // ✅ Verify OTP
//                    val enteredOtp = otpStates.joinToString("") { it.value }
//                    if (storedVerificationId != null && enteredOtp.isNotEmpty()) {
//                        val credential = PhoneAuthProvider.getCredential(storedVerificationId!!, enteredOtp)
//                        auth.signInWithCredential(credential)
//                            .addOnCompleteListener { task ->
//                                if (task.isSuccessful) {
//                                    println("✅ Login Success")
//                                    navController.navigate(Screen.DoctorMain.route) {
//                                        popUpTo(Screen.Auth.route) { inclusive = true }
//                                    }
//                                } else {
//                                    println("❌ Invalid OTP: ${task.exception?.message}")
//                                }
//                            }
//                    }
//                }
//            }
//        )
        SendOtpButton(
            text = if (!isOtpSent) LoginScreenStrings.SEND_OTP else LoginScreenStrings.LOGIN,
            modifier = Modifier.padding(horizontal = 34.dp, vertical = 16.dp),
            onClick = {
                if (!isOtpSent) {
                    // ✅ Send OTP
                    if (phoneNumber.length < 10) {
                        println("❌ Invalid phone number")
                    } else {
                        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                                // Auto-fill OTP if SMS Retriever catches it
                                credential.smsCode?.let { code ->
                                    otpStates.forEachIndexed { index, state ->
                                        if (index < code.length) state.value = code[index].toString()
                                    }
                                    // Direct login if auto-verification succeeds
                                    auth.signInWithCredential(credential)
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                println("✅ Auto Login Success")
                                                saveDoctorInfo("+91$phoneNumber")
                                                navController.navigate(Screen.DoctorMain.route) {
                                                    popUpTo(Screen.Auth.route) { inclusive = true }
                                                }

                                            }
                                        }
                                }
                            }



                            override fun onVerificationFailed(e: FirebaseException) {
                                println("❌ OTP Send Failed: ${e.message}")
                            }

                            override fun onCodeSent(
                                verificationId: String,
                                token: PhoneAuthProvider.ForceResendingToken
                            ) {
                                println("✅ OTP Sent to $phoneNumber")
                                isOtpSent = true
                                // ❌ FIX: don’t shadow variable
                                storedVerificationId = verificationId
                            }
                        }

                        val options = PhoneAuthOptions.newBuilder(auth)
                            .setPhoneNumber("+91$phoneNumber")
                            .setTimeout(60L, TimeUnit.SECONDS)
                            .setActivity(activity)
                            .setCallbacks(callbacks)
                            .build()

                        PhoneAuthProvider.verifyPhoneNumber(options)
                    }
                } else {
                    // ✅ Verify OTP
                    val enteredOtp = otpStates.joinToString("") { it.value }
                    if (storedVerificationId != null && enteredOtp.length == 6) {
                        val credential = PhoneAuthProvider.getCredential(storedVerificationId!!, enteredOtp)
                        auth.signInWithCredential(credential)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    println("✅ Login Success")
                                    saveDoctorInfo("+91$phoneNumber")
                                    navController.navigate(Screen.DoctorMain.route) {
                                        popUpTo(Screen.Auth.route) { inclusive = true }
                                    }
                                } else {
                                    println("❌ Invalid OTP: ${task.exception?.message}")
                                }
                            }
                    } else {
                        println("❌ Please enter OTP")
                    }
                }
            }
        )
    }
}


private fun saveDoctorInfo(phoneNumber: String) {
    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()
    val userId = auth.currentUser?.uid ?: return   // ✅ avoid crash if null

    val doctorData = mapOf(
        "uid" to userId,
        "phoneNumber" to phoneNumber,
        "createdAt" to Timestamp.now()
    )

    firestore.collection("doctors")
        .document(userId)
        .set(doctorData)
        .addOnSuccessListener {
            println("✅ Doctor data added")
        }
        .addOnFailureListener { e ->
            println("❌ Failed to save doctor data: ${e.message}")
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
            color = Color.Black
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
@Composable
fun SendOtpButton(
    text: String,
    modifier: Modifier = Modifier,
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
                )
            )
            .clickable(onClick = onClick),
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



