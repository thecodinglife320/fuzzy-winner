plugins {
   alias(libs.plugins.android.application)
   alias(libs.plugins.kotlin.android)
   alias(libs.plugins.kotlin.compose)
}

android {
   namespace = "com.ad.amphibians"
   compileSdk = 35

   defaultConfig {
      applicationId = "com.ad.amphibians"
      minSdk = 26
      targetSdk = 35
      versionCode = 1
      versionName = "1.0"

      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
   }

   buildTypes {
      release {
         isMinifyEnabled = true
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

   implementation(platform(libs.androidx.compose.bom))
   implementation(libs.androidx.activity.compose)
   implementation(libs.material3)
   implementation(libs.ui)
   implementation(libs.ui.tooling.preview)
   implementation(libs.androidx.core.ktx)
   implementation(libs.androidx.lifecycle.runtime.ktx)
   implementation(libs.androidx.lifecycle.viewmodel.compose)
   implementation(libs.androidx.ui.text.google.fonts)

   testImplementation(libs.junit.junit)
   testImplementation(libs.kotlinx.coroutines.test)

   //network
   implementation(libs.retrofit)

   //retrofit converter
   implementation(libs.converter.gson)

   //coil
   implementation(libs.coil.compose)

   androidTestImplementation(libs.androidx.junit)
   androidTestImplementation(libs.androidx.espresso.core)
   androidTestImplementation(platform(libs.androidx.compose.bom))
   androidTestImplementation(libs.androidx.ui.test.junit4)

   debugImplementation(libs.androidx.ui.tooling)
   debugImplementation(libs.androidx.ui.test.manifest)
}