package com.ad.dogify.di

import com.ad.dogify.model.FetchBreedsUseCase
import com.ad.dogify.model.GetBreedsUseCase
import com.ad.dogify.model.ToggleFavouriteStateUseCase
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

private val usecaseModule = module {
   factory { GetBreedsUseCase() }
   factory { FetchBreedsUseCase() }
   factory { ToggleFavouriteStateUseCase() }
}

private val sharedModules = listOf(usecaseModule)

fun initKoin(appDeclaration: KoinAppDeclaration = {})
     = startKoin {
   appDeclaration()
   modules(sharedModules)
}