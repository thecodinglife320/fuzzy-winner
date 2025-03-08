package com.ad.dogify.model

class GetBreedsUseCase {
   suspend fun invoke() = listOf(Breed("Test get", ""))
}