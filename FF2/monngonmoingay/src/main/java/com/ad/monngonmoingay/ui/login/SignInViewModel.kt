package com.ad.monngonmoingay.ui.login

import com.ad.monngonmoingay.data.model.ErrorMessage
import com.ad.monngonmoingay.data.repository.AuthRepository
import com.ad.monngonmoingay.ui.shared.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
   private val authRepository: AuthRepository
) : MainViewModel() {

   private val _shouldRestartAppFlow = MutableStateFlow(false)

   val shouldRestartAppFLow: StateFlow<Boolean>
      get() = _shouldRestartAppFlow.asStateFlow()

   fun signIn(
      email: String,
      password: String,
      showErrorSnackBar: (ErrorMessage) -> Unit
   ) {
      launchCatching(
         showErrorSnackBar,
         block = {
            authRepository.signIn(email, password)
            _shouldRestartAppFlow.value = true
         }
      )
   }
}