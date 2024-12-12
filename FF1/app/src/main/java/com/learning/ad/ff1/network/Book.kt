package com.learning.ad.ff1.network
data class Book(
   val volumeInfo: VolumeInfo,
)
data class VolumeInfo(
   val title: String = "Unknown",
   val authors: List<String> = listOf("Unknown"),
   val publisher: String = "Unknown",
   val description: String = "No description available",
   val infoLink: String = "",
)