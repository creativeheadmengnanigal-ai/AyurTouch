plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    // CocoaPods for iOS
    id("org.jetbrains.kotlin.native.cocoapods") version "1.9.20" apply false
    // Firebase
    id("com.google.gms.google-services") version "4.4.0" apply false
    kotlin("plugin.serialization") version "2.1.21"
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {

        classpath ("com.google.gms:google-services:4.4.3")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:2.1.0")
        classpath ("com.android.tools.build:gradle:8.13.0")

    }
}
