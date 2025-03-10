package com.ad.dogify.di

import com.ad.dogify.api.BreedsApi
import com.ad.dogify.repository.BreedsRemoteSource
import com.ad.dogify.repository.BreedsRepository
import com.ad.dogify.usecase.FetchBreedsUseCase
import com.ad.dogify.usecase.GetBreedsUseCase
import com.ad.dogify.usecase.ToggleFavouriteStateUseCase
import com.ad.dogify.util.getDispatcherProvider
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

private val utilModule = module {
   factory { getDispatcherProvider() }
}

private val apiModule = module {
   factory { BreedsApi() }
}

private val repositoryModule = module {
   single { BreedsRepository(get()) }
   factory { BreedsRemoteSource(get(), get()) }
}

private val usecaseModule = module {
   factory { GetBreedsUseCase() }
   factory { FetchBreedsUseCase() }
   factory { ToggleFavouriteStateUseCase() }
}

private val sharedModules = listOf(usecaseModule, repositoryModule, apiModule, utilModule)

fun initKoin(appDeclaration: KoinAppDeclaration = {})
     = startKoin {
   appDeclaration()
   modules(sharedModules)
}