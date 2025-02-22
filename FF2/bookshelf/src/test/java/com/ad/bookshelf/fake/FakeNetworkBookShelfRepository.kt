package com.ad.bookshelf.fake

import com.ad.bookshelf.data.BookShelfRepository
import com.ad.bookshelf.model.Book
import com.ad.bookshelf.model.SearchResult

class FakeNetworkBookShelfRepository : BookShelfRepository {
   override suspend fun searchBooks(searchTerm: String): SearchResult {
      return FakeBookShelfDataSource.searchResult
   }

   override suspend fun getBook(bookId: String): Book {
      return FakeBookShelfDataSource.book
   }
}