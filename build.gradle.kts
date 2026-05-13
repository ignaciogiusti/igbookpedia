plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.room) apply false

    //    Firebase
    // Make sure that you have the Google services Gradle plugin dependency
    id("com.google.gms.google-services") version "4.4.4" apply false

//    id("com.android.application") version "9.1.1" apply false

    // Add the dependency for the App Distribution Gradle plugin
    id("com.google.firebase.appdistribution") version "5.2.1" apply false
}