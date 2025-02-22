package com.ad.bookshelf.ui.stateholder

import com.ad.bookshelf.fake.FakeBookShelfDataSource
import com.ad.bookshelf.fake.FakeNetworkBookShelfRepository
import com.ad.bookshelf.rule.TestDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class BookShelfViewModelTest {

   @get:Rule
   val testDispatcher = TestDispatcherRule()

   @Test
   fun bookShelfViewModel_init_verifyBookShelfUiStateSuccess() =
      runTest {
         val bookShelfViewModel = BookShelfViewModel(
            bookShelfRepository = FakeNetworkBookShelfRepository()
         )
         assertEquals(
            BookShelfUiState.Success(FakeBookShelfDataSource.books),
            bookShelfViewModel.bookShelfUiState
         )

      }


}