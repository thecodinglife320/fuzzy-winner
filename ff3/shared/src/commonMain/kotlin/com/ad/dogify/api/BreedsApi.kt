package com.ad.dogify.api

import com.ad.dogify.api.model.BreedImageResponse
import com.ad.dogify.api.model.BreedsResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

internal class BreedsApi : KtorApi() {
suspend fun getBreeds() =
      client.get {
         apiUrl("breeds/list")
      }.body<BreedsResponse>()

   suspend fun getRandomBreedImageFor(breed: String) =
      client.get {
         apiUrl("breed/$breed/images/random")
      }.body<BreedImageResponse>()
}