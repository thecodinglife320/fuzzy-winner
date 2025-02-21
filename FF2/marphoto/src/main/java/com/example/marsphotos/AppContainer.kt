package com.example.marsphotos

import com.example.marsphotos.data.MarsPhotosRepository
import com.example.marsphotos.data.NetworkMarsPhotosRepository
import com.example.marsphotos.network.MarsApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
   val marsPhotosRepository: MarsPhotosRepository
}

class DefaultAppContainer : AppContainer {

   private val baseUrl =
      "https://android-kotlin-fun-mars-server.appspot.com"

   private val retrofit = Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl(baseUrl)
      .build()

   private val retrofitService: MarsApiService by lazy {
      retrofit.create(MarsApiService::class.java)
   }

   override val marsPhotosRepository: MarsPhotosRepository by lazy {
      NetworkMarsPhotosRepository(retrofitService)
   }
}