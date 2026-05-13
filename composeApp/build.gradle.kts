import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
//import com.google.firebase.appdistribution.gradle.firebaseAppDistribution

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)

    //    Firebase
    // Make sure that you have the Google services Gradle plugin
    id("com.google.gms.google-services")
//    id("com.android.application")
    // Add the App Distribution Gradle plugin
    id("com.google.firebase.appdistribution")
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
    
    jvm("desktop")

    room {
        schemaDirectory("$projectDir/schemas")
    }

    sourceSets {
        val desktopMain by getting
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            implementation(libs.ktor.client.okhttp)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.jetbrains.compose.navigation)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.androidx.room.runtime)
            implementation(libs.sqlite.bundled)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            api(libs.koin.core)

            implementation(libs.bundles.ktor)
            implementation(libs.bundles.coil)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.ktor.client.okhttp)
        }
        nativeMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }

        dependencies {
            ksp(libs.androidx.room.compiler)
            implementation(platform("com.google.firebase:firebase-bom:34.13.0"))
            // Add the dependency for the Firebase SDK for Google Analytics
            implementation("com.google.firebase:firebase-analytics")
            implementation("com.google.firebase:firebase-auth")
            implementation("com.google.firebase:firebase-firestore")
        }
    }
}

android {
    namespace = "com.plcoding.bookpedia"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.plcoding.bookpedia"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    flavorDimensions += listOf("membership")
    productFlavors {
        create("paid") {
            applicationIdSuffix = ".paid"
            dimension = "membership"
//            firebaseAppDistribution {
//                releaseNotes = "Release notes for full version"
//                testers = "full@testers.com"
//            }
        }
        create("free") {
            applicationIdSuffix = ".free"
            dimension = "membership"
//            firebaseAppDistribution {
//                releaseNotes = "Release notes for full version"
//                testers = "full@testers.com"
//            }
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    signingConfigs {
        create("release") {
            storeFile = file("release.keystore")
            storePassword = System.getenv("ANDROID_KEYSTORE_PASSWORD")
            keyAlias = System.getenv("ANDROID_KEY_ALIAS")
            keyPassword = System.getenv("ANDROID_KEY_PASSWORD")
        }
        val keystoreFile = file("prod.keystore")
        create("prod") {
            if (keystoreFile.exists()) {
                storeFile = keystoreFile
                storePassword = System.getenv("ANDROID_KEYSTORE_PASSWORD")
                keyAlias = System.getenv("ANDROID_KEY_ALIAS")
                keyPassword = System.getenv("ANDROID_KEY_PASSWORD")
            } else {
                val debugConfig = getByName("debug")
                storeFile = debugConfig.storeFile
                storePassword = debugConfig.storePassword
                keyAlias = debugConfig.keyAlias
                keyPassword = debugConfig.keyPassword
            }
        }
    }
    buildTypes {
        debug {
            isMinifyEnabled = false
//            buildConfigField("String", "BASE_URL", "\"https://openlibrary.org/\"")
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = true
        }
        release {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("release")
//            buildConfigField("String", "BASE_URL", "\"https://live.openlibrary.org/\"")
//            getDefaultProguardFile("proguard-android-optimize.txt")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
//            firebaseAppDistribution {
//                artifactType = "AAB"
//                releaseNotesFile = "/path/to/releasenotes.txt"
//                testers = "ali@example.com, bri@example.com, cal@example.com"
//            }
        }
        create ("prod") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("prod")
            isDebuggable = true
//            buildConfigField("String", "BASE_URL", "\"https://live.openlibrary.org/\"")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "com.plcoding.bookpedia.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.plcoding.bookpedia"
            packageVersion = "1.0.0"
        }
    }
}