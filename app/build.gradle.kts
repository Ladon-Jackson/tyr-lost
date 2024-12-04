plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    //kotlin("plugin.serialization") version "1.9.0" //TODO I YOLO SET THIS TO MAKE IT WORK
}

android {
    namespace = "com.example.tyrlost"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tyrlost"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.compose)

    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    // ViewModel utilities for Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)
    // Lifecycles only (without ViewModel or LiveData)
    implementation(libs.androidx.lifecycle.runtime.ktx.v287)
    // Lifecycle utilities for Compose
    implementation(libs.androidx.lifecycle.runtime.compose)
    // Saved state module for ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
    // Annotation processor
    kapt(libs.androidx.lifecycle.compiler)
    // alternately - if using Java8, use the following instead of lifecycle-compiler
    implementation(libs.androidx.lifecycle.common.java8)
    // optional - helpers for implementing LifecycleOwner in a Service
    implementation(libs.androidx.lifecycle.service)
    // optional - ProcessLifecycleOwner provides a lifecycle for the whole application process
    implementation(libs.androidx.lifecycle.process)
    // optional - ReactiveStreams support for LiveData
    implementation(libs.androidx.lifecycle.reactivestreams.ktx)
    // optional - Test helpers for LiveData
    testImplementation(libs.androidx.core.testing)
    // optional - Test helpers for Lifecycle runtime
    testImplementation(libs.androidx.lifecycle.runtime.testing)
    implementation(libs.coil.compose)

    implementation(libs.androidx.navigation.compose)
    // implementation(libs.kotlinx.serialization.json) //TODO I YOLOD THIS TO MAKE IT WORK
}