package com.learning.ad.ff1.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.ad.ff1.network.Book
import com.learning.ad.ff1.network.BookFinderApi
import kotlinx.coroutines.launch

enum class BookApiStatus { LOADING, ERROR, DONE, EMPTY }
class BookViewModel :ViewModel(){
   private val _status = MutableLiveData(BookApiStatus.EMPTY)
   val status: LiveData<BookApiStatus> = _status

   private val _books = MutableLiveData<List<Book>>()
   val books: LiveData<List<Book>> = _books

   private val _totalItems = MutableLiveData<String>()
   val totalItem: LiveData<String> = _totalItems
   fun searchBooks(query: String) {
      _status.value = BookApiStatus.LOADING
      viewModelScope.launch {
         try {
            val response = BookFinderApi.retrofitService.getDetails(
               searchString = query,
               maxResults = "10",
               printType = "books"
            )
            _totalItems.value = "10 out of ${response.totalItems} books found."
            _books.value = response.items
            _status.value = BookApiStatus.DONE
            _books.value!!.forEach {
               Log.d(TAG, it.volumeInfo.title)
            }
         }
         catch (e: Exception) {
            _status.value = BookApiStatus.ERROR
            Log.d(TAG1, e.message.toString())
         }
      }
   }
   companion object{
      const val TAG = "BookViewModel"
      const val TAG1 = "error"
   }
   fun resetStatus() {
      _status.value = BookApiStatus.EMPTY
   }
}
