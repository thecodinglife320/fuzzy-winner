package com.ad.bookshelf.ui.stateholder

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ad.bookshelf.BookShelfApplication
import com.ad.bookshelf.data.BookShelfRepository
import com.ad.bookshelf.model.Book
import kotlinx.coroutines.launch

class BookShelfViewModel(
   private val bookShelfRepository: BookShelfRepository
) : ViewModel() {

   var bookShelfUiState: BookShelfUiState by mutableStateOf(BookShelfUiState.Loading)
      private set

   init {
      searchBooks()
   }

   private fun searchBooks(searchTerm: String = "jetpack compose") {

      val books = mutableListOf<Book>()

      viewModelScope.launch {
         bookShelfUiState = try {

            bookShelfRepository.searchBooks(searchTerm).items.forEach {
               books.add(bookShelfRepository.getBook(it.id))
            }

            BookShelfUiState.Success(books)
         } catch (e: Exception) {
            BookShelfUiState.Error
         }
      }
   }

   companion object {
      val Factory: ViewModelProvider.Factory = viewModelFactory {
         initializer {
            val application = (this[APPLICATION_KEY] as BookShelfApplication)
            val bookShelfRepository = application.container.bookShelfRepository
            BookShelfViewModel(bookShelfRepository)
         }
      }
   }
}