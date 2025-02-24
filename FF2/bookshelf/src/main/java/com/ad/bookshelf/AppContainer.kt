package com.ad.bookshelf

import com.ad.bookshelf.data.BookShelfRepository
import com.ad.bookshelf.data.NetworkBookShelfRepository
import com.ad.bookshelf.network.BookShelfApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
   val bookShelfRepository: BookShelfRepository
}

class DefaultAppContainer : AppContainer {

   private val baseUrl = "https://www.googleapis.com/books/v1/"

   private val retrofit = Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl(baseUrl)
      .build()

   private val retrofitService: BookShelfApiService by lazy {
      retrofit.create(BookShelfApiService::class.java)
   }

   override val bookShelfRepository: BookShelfRepository by lazy {
      NetworkBookShelfRepository(retrofitService)
   }
}