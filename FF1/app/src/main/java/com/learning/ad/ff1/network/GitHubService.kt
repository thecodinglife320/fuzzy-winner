package com.learning.ad.ff1.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface GitHubAPIInterface {
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

object APIService {
   val gitHubAPI: GitHubAPIInterface by lazy { retrofit.create(GitHubAPIInterface::class.java) }
}