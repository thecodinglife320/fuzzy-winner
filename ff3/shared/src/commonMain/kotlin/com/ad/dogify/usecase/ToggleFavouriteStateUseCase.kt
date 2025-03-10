package com.ad.dogify.usecase

import com.ad.dogify.model.Breed

class ToggleFavouriteStateUseCase {
   suspend operator fun invoke(breed: Breed) {}
}