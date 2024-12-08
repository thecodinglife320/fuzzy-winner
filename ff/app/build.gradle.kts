plugins {
   alias(libs.plugins.android.application)
   alias(libs.plugins.kotlin.android)
   id ("kotlin-kapt")
   id ("androidx.navigation.safeargs.kotlin")
   //alias(libs.plugins.devtoolsKsp)
}

android {
   namespace = "com.learning.ad.ff"
   compileSdk = 35

   defaultConfig {
      applicationId = "com.learning.ad.ff"
      minSdk = 30
      targetSdk = 34
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
      viewBinding = true
      dataBinding = true
   }
}

dependencies {
   implementation(libs.androidx.core.ktx)
   implementation(libs.androidx.appcompat)
   implementation(libs.material)
   implementation(libs.androidx.activity)
   implementation(libs.androidx.constraintlayout)
   implementation(libs.androidx.navigation.fragment)
   implementation(libs.androidx.legacy.support.v4)
   implementation(libs.api.kotlin)
   implementation(libs.lifecycle.runtime.ktx)
   implementation(libs.androidx.lifecycle.viewmodel.ktx)
   implementation(libs.androidx.navigation.ui.ktx)
   implementation(libs.androidx.fragment.ktx)
   implementation(libs.kotlinx.coroutines.core)
   implementation(libs.kotlinx.coroutines.android)

   //room
   implementation(libs.androidx.room.ktx)
   kapt(libs.androidx.room.compiler)
   implementation(libs.androidx.room.runtime)

   testImplementation(libs.junit)
   androidTestImplementation(libs.androidx.junit)
   androidTestImplementation(libs.androidx.espresso.core)
}