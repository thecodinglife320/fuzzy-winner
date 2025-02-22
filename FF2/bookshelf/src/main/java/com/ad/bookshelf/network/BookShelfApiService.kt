package com.ad.bookshelf.network

import com.ad.bookshelf.model.Book
import com.ad.bookshelf.model.SearchResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookShelfApiService {

   @GET("/books/v1/volumes")
   suspend fun searchBooks(@Query("q") query: String): SearchResult

   @GET("volumes/{volumeId}")
   suspend fun getBook(@Path("volumeId") volumeId: String): Book

}
