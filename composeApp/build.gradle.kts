import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("com.google.gms.google-services")

}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation(compose.preview)

            implementation ("androidx.compose.material3:material3:1.2.0")

            // Firebase Auth for Android
            implementation("com.google.firebase:firebase-auth:24.0.1")
            implementation(platform("com.google.firebase:firebase-bom:33.1.2"))


            implementation("com.google.android.gms:play-services-auth:21.3.0")
            implementation ("com.google.android.gms:play-services-auth-api-phone:18.2.0")
            implementation("com.google.android.gms:play-services-safetynet:18.1.0")

            // AndroidX Lifecycle (for ViewModel)
            implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.1")
            implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.1")
            implementation ("androidx.activity:activity-compose:1.8.2")

            //rive animation
            implementation("app.rive:rive-android:9.6.5")
            implementation ("androidx.startup:startup-runtime:1.1.1")

            implementation("com.google.firebase:firebase-auth:24.0.1")
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)




            // Coroutines
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

            // KMM ViewModel
            implementation("com.rickclephas.kmm:kmm-viewmodel-core:1.0.0-ALPHA-7")

            // Multiplatform Settings (for storing tokens if needed)
            implementation("com.russhwolf:multiplatform-settings:1.1.1")

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "org.ayurtouch.project"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.ayurtouch.project"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        ndk {
            abiFilters += listOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64")
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    packagingOptions {
        pickFirsts += "/lib/**/libapp_rive_runtime.so"
    }
    buildFeatures {
        compose = true
    }


    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11

    }

}

dependencies {


    debugImplementation(compose.uiTooling)
    implementation(libs.androidx.navigation.compose)
    // Compose UI
    implementation("androidx.compose.ui:ui:1.5.4")
    implementation("androidx.compose.material:material:1.5.4")
    implementation("androidx.compose.foundation:foundation:1.8.3")

    // Firebase
    implementation("com.google.firebase:firebase-auth:24.0.1")
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))
    implementation ("androidx.credentials:credentials:1.5.0")
    implementation ("androidx.credentials:credentials-play-services-auth:1.5.0")
    implementation ("com.google.android.libraries.identity.googleid:googleid:1.1.1")

    implementation("com.google.firebase:firebase-firestore:25.0.0")
    implementation("com.google.android.gms:play-services-auth:21.2.0")
    implementation("com.google.firebase:firebase-messaging:25.0.0")


    implementation("com.google.firebase:firebase-functions:22.0.0")

    
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.1")
    implementation ("com.google.android.gms:play-services-auth-api-phone:18.2.0")
    // AndroidX Lifecycle (for ViewModel)
    implementation ("androidx.activity:activity-compose:1.10.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    //rive animation
    implementation("app.rive:rive-android:9.6.5")
    implementation ("androidx.startup:startup-runtime:1.1.1")


    // animation
    implementation ("androidx.compose.material:material:1.6.1")
    implementation ("androidx.compose.animation:animation:1.6.1")
    //coil
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("io.coil-kt:coil-gif:2.4.0")
    //dataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    //json
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("com.google.code.gson:gson:2.8.9")

}

