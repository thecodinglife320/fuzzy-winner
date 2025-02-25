/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
plugins {
   id("com.android.application")
   id("com.google.devtools.ksp") version "2.1.0-1.0.29"
   id("org.jetbrains.kotlin.android")
   id("org.jetbrains.kotlin.plugin.compose")
}

android {
   namespace = "com.example.busschedule"
   compileSdk = 35

   defaultConfig {
      applicationId = "com.example.busschedule"
      minSdk = 24
      targetSdk = 35
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
      sourceCompatibility = JavaVersion.VERSION_17
      targetCompatibility = JavaVersion.VERSION_17
   }
   kotlinOptions {
      jvmTarget = "17"
   }
   buildFeatures {
      compose = true
   }
   packaging {
      resources {
         excludes += "/META-INF/{AL2.0,LGPL2.1}"
      }
   }
}

dependencies {

   implementation(platform(libs.androidx.compose.bom))
   implementation(libs.androidx.activity.compose)
   implementation(libs.androidx.compose.material3.material3)
   implementation(libs.androidx.compose.ui.ui)
   implementation(libs.androidx.compose.ui.ui.tooling)
   implementation(libs.androidx.compose.ui.ui.tooling.preview)
   implementation(libs.androidx.lifecycle.runtime.ktx)
   implementation(libs.androidx.lifecycle.viewmodel.compose)
   implementation(libs.androidx.navigation.compose)

   implementation(libs.androidx.room.runtime)
   ksp(libs.androidx.room.compiler)
   implementation(libs.androidx.room.ktx)

   // Testing
   androidTestImplementation(libs.androidx.espresso.core)
   androidTestImplementation(libs.androidx.junit)
}
