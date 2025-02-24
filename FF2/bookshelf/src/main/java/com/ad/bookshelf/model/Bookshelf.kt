package com.ad.bookshelf.model

data class SearchResult(
   val items: List<Item>
)

data class Item(
   val id: String
)

data class Book(
   val id:String,
   val volumeInfo: VolumeInfo
)

data class VolumeInfo(
   val imageLinks: ImageLinks
)

data class ImageLinks(
   val thumbnail: String
)