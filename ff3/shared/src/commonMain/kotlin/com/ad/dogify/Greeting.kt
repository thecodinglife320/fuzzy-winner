package com.ad.dogify

import com.ad.dogify.model.Breed
import com.ad.dogify.model.FetchBreedsUseCase
import com.ad.dogify.model.GetBreedsUseCase
import com.ad.dogify.model.ToggleFavouriteStateUseCase

class Greeting {
    private val platform: Platform = getPlatform()

    suspend fun greet(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.appendLine(platform.name)
        stringBuilder.appendLine(FetchBreedsUseCase().invoke())
        stringBuilder.appendLine(GetBreedsUseCase().invoke())
        stringBuilder.appendLine(ToggleFavouriteStateUseCase().invoke(
           breed = Breed(
               name = "",
               imageUrl = "",
               isFavorite = false
           )
        ))
        return stringBuilder.toString()
    }
}