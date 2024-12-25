package com.learning.ad.ff1

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {
   @GET
   suspend fun getRepos(@Url url: String): List<Repo>
}

private val moshi = Moshi.Builder()
   .add(KotlinJsonAdapterFactory())
   .build()

private val retrofit = Retrofit.Builder()
   .addConverterFactory(MoshiConverterFactory.create(moshi))
   .baseUrl("https://api.github.com/")
   .build()

object GitHubService {
   val gitHubAPI: ApiInterface by lazy { retrofit.create(ApiInterface::class.java) }
}
@Suppress("PropertyName")
data class Repo(
   val id: Int,
   val name: String,
   val full_name: String,
   val description: String?,
   val owner: Owner
)
@Suppress("PropertyName")
data class Owner(
   val login: String,
   val id: Int,
   val avatar_url: String,
   val url: String
)