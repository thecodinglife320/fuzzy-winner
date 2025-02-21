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
   id("org.jetbrains.kotlin.android")
   id("org.jetbrains.kotlin.plugin.compose")
}

android {
   namespace = "com.example.marsphotos"
   compileSdk = 35

   defaultConfig {
      applicationId = "com.example.marsphotos"
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
      sourceCompatibility = JavaVersion.VERSION_1_8
      targetCompatibility = JavaVersion.VERSION_1_8
   }
   kotlinOptions {
      jvmTarget = "1.8"
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

   // Import the Compose BOM
   implementation(platform(libs.androidx.compose.bom))
   implementation(libs.androidx.activity.compose)
   implementation(libs.material3)
   implementation(libs.ui)
   implementation(libs.ui.tooling.preview)
   implementation(libs.androidx.core.ktx)
   implementation(libs.androidx.lifecycle.runtime.ktx)
   implementation(libs.androidx.lifecycle.viewmodel.compose)

   //network
   implementation(libs.retrofit)

   //retrofit converter
   implementation(libs.converter.gson)

   //coil
   implementation(libs.coil.compose)

   //testing
   testImplementation(libs.junit.junit)
   testImplementation(libs.kotlinx.coroutines.test)

   debugImplementation(libs.ui.test.manifest)
   debugImplementation(libs.ui.tooling)
}
