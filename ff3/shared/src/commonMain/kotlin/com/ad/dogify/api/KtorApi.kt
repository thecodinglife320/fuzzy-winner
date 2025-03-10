package com.ad.dogify.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.path
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal abstract class KtorApi {

   private val jsonConfiguration = Json {
      prettyPrint = true
      ignoreUnknownKeys = true
   }

   val client = HttpClient {
      install(ContentNegotiation) {
         json(jsonConfiguration)
      }
      install(Logging) {
         logger = Logger.SIMPLE
         level = LogLevel.ALL
      }
   }

   /**
    * Use this method for configuring the request url
    */
   fun HttpRequestBuilder.apiUrl(path: String) {
      url {
         takeFrom("https://dog.ceo")
         path("api", path)
      }
   }
}