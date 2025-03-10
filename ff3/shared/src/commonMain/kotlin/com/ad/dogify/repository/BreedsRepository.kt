package com.ad.dogify.repository

import com.ad.dogify.model.Breed
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.supervisorScope

class BreedsRepository internal constructor(
   private val remoteSource: BreedsRemoteSource
) {

   suspend fun get() = fetch()

   private suspend fun fetch() = supervisorScope {
      remoteSource.getBreeds().map {
         async { Breed(name = it, imageUrl =
            remoteSource.getBreedImage(it)) }
      }.awaitAll()
   }
}