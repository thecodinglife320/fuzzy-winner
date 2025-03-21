package com.ad.luchtray

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
   runBlocking {
      println("Weather forecast")
      println(getWeatherReport())
      println("Have a good day!")
   }
}

suspend fun getForecast(): String {
   delay(1000)
   return "Sunny"
}

suspend fun getTemperature(): String {
   delay(500)
   throw AssertionError("Temperature is invalid")
}

suspend fun getWeatherReport(): String {
   return coroutineScope {
      "${
         async {
            getForecast()
         }.await()
      } ${
         async {
            try {
               getTemperature()
            } catch (e: AssertionError) {
               "Error: ${e.message}"
            }
         }.cancel()
      }"
   }
}