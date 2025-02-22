package com.ad.bookshelf.data

import com.ad.bookshelf.fake.FakeBookShelfApiService
import com.ad.bookshelf.fake.FakeBookShelfDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkBookShelfRepositoryTest {

   @Test
   fun networkBookShelfRepository_searchBooks_verifySearchResult() =
      runTest {
         val repository = NetworkBookShelfRepository(
            bookShelfApiService = FakeBookShelfApiService()
         )
         assertEquals(FakeBookShelfDataSource.searchResult, repository.searchBooks(""))
      }

   @Test
   fun networkBookShelfRepository_getBook_verifyBook() =
      runTest {
         val repository = NetworkBookShelfRepository(
            bookShelfApiService = FakeBookShelfApiService()
         )
         assertEquals(FakeBookShelfDataSource.book, repository.getBook(""))

      }
}