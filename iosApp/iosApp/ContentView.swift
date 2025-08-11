import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}


// iosApp/iosApp/ContentView.swift
struct ContentView: View {
    @ObservedObject var viewModel = AuthViewModel(authRepository: AuthRepository())

    var body: some View {
        Group {
            if viewModel.authState is AuthStateSuccess {
                MainView()
            } else {
                LoginView(viewModel: viewModel)
            }
        }
    }
}
