package com.ad.luchtray

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalStdlibApi::class)
fun main() {
   var job: Job
   runBlocking {

      launch {
         job = launch(Dispatchers.Default) {
            delay(1000)
            println("10 results found.")
            launch(Dispatchers.Default) {
               println("22 results found.")
            }
         }

         launch() {
            delay(1000)
            println("20 results found.")
         }

         job.cancel()
      }
      println("Search canceled.")
   }
}