package com.ad.bookshelf.fake

import com.ad.bookshelf.model.Book
import com.ad.bookshelf.model.ImageLinks
import com.ad.bookshelf.model.Item
import com.ad.bookshelf.model.SearchResult
import com.ad.bookshelf.model.VolumeInfo

object FakeBookShelfDataSource {

   val searchResult = SearchResult(
      items = listOf(Item("1"), Item("2"))
   )

   val book = Book(
      volumeInfo = VolumeInfo(imageLinks = ImageLinks("thumbnail_url")),
      id = "1"
   )

   val books = listOf(
      Book(
         volumeInfo = VolumeInfo(imageLinks = ImageLinks("thumbnail_url")),
         id = "1"
      ),
      Book(
         volumeInfo = VolumeInfo(imageLinks = ImageLinks("thumbnail_url")),
         id = "1"
      )
   )
}