package com.ad.bookshelf.ui.stateholder

import com.ad.bookshelf.model.Book

sealed interface BookShelfUiState {
   data class Success(val books: List<Book>) : BookShelfUiState
   data object Error : BookShelfUiState
   data object Loading : BookShelfUiState

}