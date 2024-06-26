import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.room)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.spherixlabs.trekscape"
    compileSdk = libs.versions.projectCompileSdkVersion.get().toInt()

    defaultConfig {
        applicationId = libs.versions.projectApplicationId.get()
        minSdk        = libs.versions.projectMinSdkVersion.get().toInt()
        targetSdk     = libs.versions.projectTargetSdkVersion.get().toInt()
        versionCode   = libs.versions.projectVersionCode.get().toInt()
        versionName   = libs.versions.projectVersionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            keyAlias      = gradleLocalProperties(rootDir, rootProject.providers)
                                .getProperty("KEY_ALIAS")
            keyPassword   = gradleLocalProperties(rootDir, rootProject.providers)
                                .getProperty("KEY_PASSWORD")
            storeFile     = file(
                                gradleLocalProperties(rootDir, rootProject.providers)
                                    .getProperty("KEY_STORE_FILE")
                            )
            storePassword = gradleLocalProperties(rootDir, rootProject.providers)
                                .getProperty("KEY_PASSWORD")
        }
    }

    buildTypes {
        all {
            val googleMapsApiKey = gradleLocalProperties(rootDir, rootProject.providers)
                .getProperty("KEY_GOOGLE_MAPS")
            val placesApiKey = gradleLocalProperties(rootDir, rootProject.providers)
                .getProperty("KEY_PLACES")
            val geminiApiKey = gradleLocalProperties(rootDir, rootProject.providers)
                .getProperty("KEY_GEMINI")
            resValue("string","GOOGLE_MAPS_API_KEY", "\"$googleMapsApiKey\"")
            buildConfigField("String","PLACES_API_KEY", "\"$placesApiKey\"")
            buildConfigField("String","GEMINI_API_KEY", "\"$geminiApiKey\"")
            signingConfig = signingConfigs.getByName("release")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {
    // Splash Screen
    implementation(libs.androidx.splashscreen)
    // Compose
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.compose.navigation)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // Maps compose
    implementation(libs.google.maps.compose)
    // Icons
    implementation(libs.androidx.material.icons.extended)
    // Kotlin
    implementation(libs.kotlin.serialization)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    // Ktor
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.logging)
    // Room
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    // Dagger Hilt
    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.android.compiler)
    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.firebase.firestore.ktx)
    // Timber logging
    implementation(libs.timber)
    //
    debugImplementation(libs.compose.ui.tooling)
}