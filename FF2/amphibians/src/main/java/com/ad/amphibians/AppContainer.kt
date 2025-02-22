package com.ad.amphibians

import com.ad.amphibians.data.AmphibiansRepository
import com.ad.amphibians.data.NetworkAmphibiansRepository
import com.ad.amphibians.network.AmphibiansApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
   val amphibiansRepository: AmphibiansRepository
}

class DefaultAppContainer : AppContainer {

   private val baseUrl =
      "https://android-kotlin-fun-mars-server.appspot.com/"

   private val retrofit = Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl(baseUrl)
      .build()

   private val retrofitService: AmphibiansApiService by lazy {
      retrofit.create(AmphibiansApiService::class.java)
   }

   override val amphibiansRepository: AmphibiansRepository by lazy {
      NetworkAmphibiansRepository(retrofitService)
   }
}