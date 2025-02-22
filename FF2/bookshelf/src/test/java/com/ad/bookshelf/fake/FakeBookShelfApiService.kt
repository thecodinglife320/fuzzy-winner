package com.ad.bookshelf.fake

import com.ad.bookshelf.model.Book
import com.ad.bookshelf.model.SearchResult
import com.ad.bookshelf.network.BookShelfApiService

class FakeBookShelfApiService : BookShelfApiService {

   override suspend fun searchBooks(query: String): SearchResult {
      return FakeBookShelfDataSource.searchResult
   }

   override suspend fun getBook(volumeId: String): Book {
      return FakeBookShelfDataSource.book
   }
}