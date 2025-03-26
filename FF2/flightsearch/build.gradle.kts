plugins {
   alias(libs.plugins.android.application)
   alias(libs.plugins.kotlin.android)
   alias(libs.plugins.kotlin.compose)
   alias(libs.plugins.google.ksp)
   alias(libs.plugins.google.hilt)
}

android {
   namespace = "com.ad.flightsearch"
   compileSdk = 35

   defaultConfig {
      applicationId = "com.ad.flightsearch"
      minSdk = 26
      targetSdk = 35
      versionCode = 1
      versionName = "1.0"

      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
      sourceCompatibility = JavaVersion.VERSION_11
      targetCompatibility = JavaVersion.VERSION_11
   }
   kotlinOptions {
      jvmTarget = "11"
   }
   buildFeatures {
      compose = true
   }
}

dependencies {

   implementation(libs.androidx.core.ktx)
   implementation(libs.androidx.lifecycle.runtime.ktx)
   implementation(libs.androidx.activity.compose)
   implementation(platform(libs.androidx.compose.bom))
   implementation(libs.androidx.compose.ui.ui)
   implementation(libs.androidx.ui.graphics)
   implementation(libs.androidx.compose.ui.ui.tooling.preview)
   implementation(libs.androidx.compose.material3.material3)
   implementation(libs.androidx.room.runtime)
   ksp(libs.androidx.room.compiler)
   implementation(libs.androidx.room.ktx)
   implementation(libs.hilt.android)
   ksp(libs.hilt.android.compiler)
   implementation(libs.androidx.hilt.navigation.compose)

   testImplementation(libs.junit.junit)

   androidTestImplementation(libs.androidx.junit)
   androidTestImplementation(libs.androidx.espresso.core)
   androidTestImplementation(platform(libs.androidx.compose.bom))
   androidTestImplementation(libs.androidx.ui.test.junit4)

   debugImplementation(libs.androidx.compose.ui.ui.tooling)
   debugImplementation(libs.androidx.ui.test.manifest)
}