package com.ad.dogify

import com.ad.dogify.model.Breed
import com.ad.dogify.usecase.FetchBreedsUseCase
import com.ad.dogify.usecase.GetBreedsUseCase
import com.ad.dogify.usecase.ToggleFavouriteStateUseCase

class Greeting {
    private val platform: Platform = getPlatform()

    suspend fun greet(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.appendLine(platform.name)
        stringBuilder.appendLine(FetchBreedsUseCase().invoke())
        //stringBuilder.appendLine(GetBreedsUseCase().invoke())
        return stringBuilder.toString()
    }
}