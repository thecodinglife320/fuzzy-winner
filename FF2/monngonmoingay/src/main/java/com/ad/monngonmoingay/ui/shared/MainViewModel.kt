package com.ad.monngonmoingay.ui.shared

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ad.monngonmoingay.R
import com.ad.monngonmoingay.data.model.ErrorMessage
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class MainViewModel : ViewModel() {

   fun launchCatching(
      showErrorSnackBar: (ErrorMessage) -> Unit = {},
      block: suspend CoroutineScope.() -> Unit
   ) =
      viewModelScope.launch(
         CoroutineExceptionHandler { _, throwable ->
            Firebase.crashlytics.recordException(throwable)
            val error = if (throwable.message.isNullOrBlank())
               ErrorMessage.IdError(R.string.generic_error) else
               ErrorMessage.StringError(throwable.message!!)
            showErrorSnackBar(error)
         },
         block = block
      )
}