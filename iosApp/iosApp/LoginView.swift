// iosApp/iosApp/LoginView.swift
struct LoginView: View {
    @ObservedObject var viewModel: AuthViewModel
    @State private var phoneNumber: String = ""
    @State private var otp: String = ""
    @State private var verificationId: String = ""
    @State private var showOtpField: Bool = false

    var body: some View {
        VStack {
            if viewModel.authState is AuthStateLoading {
                ProgressView()
            } else {
                if !showOtpField {
                    PhoneNumberField(
                        phoneNumber: $phoneNumber,
                        onSendCode: {
                            viewModel.sendVerificationCode(phoneNumber: phoneNumber)
                            if let codeSent = viewModel.authState as? AuthStateCodeSent {
                                verificationId = codeSent.verificationId
                                showOtpField = true
                            }
                        }
                    )
                } else {
                    OtpField(
                        otp: $otp,
                        onVerify: {
                            viewModel.verifyOtp(verificationId: verificationId, otp: otp)
                        }
                    )
                }

                if let error = viewModel.authState as? AuthStateError {
                    Text(error.message)
                        .foregroundColor(.red)
                }
            }
        }
        .padding()
        .onAppear {
            viewModel.checkAuthState()
        }
    }
}

struct PhoneNumberField: View {
    @Binding var phoneNumber: String
    var onSendCode: () -> Void

    var body: some View {
        VStack {
            TextField("Phone Number", text: $phoneNumber)
                .keyboardType(.phonePad)
                .textFieldStyle(RoundedBorderTextFieldStyle())

            Button(action: onSendCode) {
                Text("Send Verification Code")
                    .frame(maxWidth: .infinity)
            }
            .buttonStyle(.borderedProminent)
        }
    }
}

struct OtpField: View {
    @Binding var otp: String
    var onVerify: () -> Void

    var body: some View {
        VStack {
            Text("Enter OTP sent to your phone")
            TextField("OTP", text: $otp)
                .keyboardType(.numberPad)
                .textFieldStyle(RoundedBorderTextFieldStyle())

            Button(action: onVerify) {
                Text("Verify OTP")
                    .frame(maxWidth: .infinity)
            }
            .buttonStyle(.borderedProminent)
        }
    }
}