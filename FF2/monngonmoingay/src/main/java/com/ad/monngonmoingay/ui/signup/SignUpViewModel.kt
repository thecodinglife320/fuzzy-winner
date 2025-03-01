package com.ad.monngonmoingay.ui.signup

import com.ad.monngonmoingay.R
import com.ad.monngonmoingay.data.model.ErrorMessage
import com.ad.monngonmoingay.data.repository.AuthRepository
import com.ad.monngonmoingay.ui.shared.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
   private val authRepository: AuthRepository
) : MainViewModel() {

   private val _shouldRestartAppFlow = MutableStateFlow(false)
   val shouldRestartAppFlow
      get() = _shouldRestartAppFlow.asStateFlow()

   fun signUp(
      email: String,
      password: String,
      repeatPassword: String,
      showErrorSnackBar: (ErrorMessage) -> Unit
   ) {
      if (!email.isValidEmail()) {
         showErrorSnackBar(ErrorMessage.IdError(R.string.invalid_email))
         return
      }

      if (!password.isValidPassword()) {
         showErrorSnackBar(ErrorMessage.IdError(R.string.invalid_password))
         return
      }

      if (password != repeatPassword) {
         showErrorSnackBar(ErrorMessage.IdError(R.string.passwords_do_not_match))
         return
      }

      launchCatching(
         showErrorSnackBar = showErrorSnackBar,
         block = {
            authRepository.signUp(email, password)
            _shouldRestartAppFlow.value = true
         }
      )
   }
}