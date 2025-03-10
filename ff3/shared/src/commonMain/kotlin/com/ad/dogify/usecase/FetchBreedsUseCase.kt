package com.ad.dogify.usecase

import com.ad.dogify.model.Breed
import com.ad.dogify.repository.BreedsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FetchBreedsUseCase : KoinComponent {
   private val repository: BreedsRepository by inject()

   suspend fun invoke() =
      repository.get()
}