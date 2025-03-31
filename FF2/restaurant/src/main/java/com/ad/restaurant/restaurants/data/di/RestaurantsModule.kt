package com.ad.restaurant.restaurants.data.di

import android.content.Context
import androidx.room.Room
import com.ad.restaurant.restaurants.data.local.RestaurantsDb
import com.ad.restaurant.restaurants.data.remote.RestaurantsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RestaurantsModule {

   @Singleton
   @Provides
   fun provideDatabase(
      @ApplicationContext
      context: Context,
   ) =
      Room.databaseBuilder(
         context = context.applicationContext,
         klass = RestaurantsDb::class.java,
         name = "restaurants_db"
      )
         .fallbackToDestructiveMigration()
         .build()

   @Singleton
   @Provides
   fun provideRetrofit() = Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl("https://ff11-a857c-default-rtdb.asia-southeast1.firebasedatabase.app")
      .build()

   @Provides
   fun provideRoomDao(database: RestaurantsDb) = database.dao

   @Provides
   fun provideRetrofitApi(retrofit: Retrofit) = retrofit
      .create(RestaurantsApi::class.java)
}