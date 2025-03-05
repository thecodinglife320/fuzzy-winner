package com.ad.monngonmoingay.ui.setting

import com.ad.monngonmoingay.data.repository.AuthRepository
import com.ad.monngonmoingay.ui.shared.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
   private val authRepository: AuthRepository
) : MainViewModel() {
   private val _shouldRestartApp = MutableStateFlow(false)
   val shouldRestartApp: StateFlow<Boolean>
      get() = _shouldRestartApp.asStateFlow()

   private val _isAnonymousOrSignOut = MutableStateFlow(true)
   val isAnonymousOrSignOut: StateFlow<Boolean>
      get() = _isAnonymousOrSignOut.asStateFlow()

   fun loadCurrentUser() {
      launchCatching {
         val currentUser = authRepository.currentUser
         _isAnonymousOrSignOut.value = (currentUser == null || currentUser.isAnonymous)
      }
   }

   fun signOut() {
      launchCatching {
         authRepository.signOut()
         _shouldRestartApp.value = true
      }
   }

   fun deleteAccount() {
      launchCatching {
         authRepository.deleteAccount()
         _shouldRestartApp.value = true
      }
   }
}