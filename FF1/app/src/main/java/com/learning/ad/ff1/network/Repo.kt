package com.learning.ad.ff1.network
@Suppress("PropertyName")
data class Repo(
   val id: Int,
   val name: String,
   val full_name: String,
   val description: String?,
   val owner: Owner
)

data class Owner(
   val login: String,
   val id: Int,
   val avatar_url: String,
   val url: String
)