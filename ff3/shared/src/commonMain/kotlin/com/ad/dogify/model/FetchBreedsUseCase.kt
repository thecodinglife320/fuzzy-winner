package com.ad.dogify.model

class FetchBreedsUseCase {
   suspend fun invoke() = listOf(Breed("Test fetch", ""))
}