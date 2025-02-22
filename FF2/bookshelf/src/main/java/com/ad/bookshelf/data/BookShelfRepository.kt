package com.ad.bookshelf.data

import com.ad.bookshelf.model.Book
import com.ad.bookshelf.model.SearchResult
import com.ad.bookshelf.network.BookShelfApiService

interface BookShelfRepository {

   //search book
   suspend fun searchBooks(searchTerm: String): SearchResult

   //get individual book
   suspend fun getBook(bookId: String): Book
}

class NetworkBookShelfRepository(
   private val bookShelfApiService: BookShelfApiService
) : BookShelfRepository {
   override suspend fun searchBooks(searchTerm: String): SearchResult {
      return bookShelfApiService.searchBooks(searchTerm)
   }

   override suspend fun getBook(bookId: String): Book {
      return bookShelfApiService.getBook(bookId)
   }

}