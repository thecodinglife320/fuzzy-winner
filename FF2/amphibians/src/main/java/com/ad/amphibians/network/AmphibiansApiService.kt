package com.ad.amphibians.network

import com.ad.amphibians.model.Amphibians
import retrofit2.http.GET

interface AmphibiansApiService {

   @GET("amphibians")
   suspend fun getAmphibians(): List<Amphibians>
}
