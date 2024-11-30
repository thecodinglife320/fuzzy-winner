package com.learning.ad.ff.viewmodel

import android.util.*
import androidx.lifecycle.*
import com.haroldadmin.cnradapter.*
import com.learning.ad.ff.observer.*
import kotlinx.coroutines.*
import org.retroachivements.api.*
import org.retroachivements.api.data.*
import org.retroachivements.api.data.pojo.*
import org.retroachivements.api.data.pojo.user.*

class UserViewModel : ViewModel(){
   private val _profile = MutableLiveData<String>()
   val profile = _profile
   fun fetchProfileUser(userName:String){
      viewModelScope.launch {
         val credentials = RetroCredentials(userName, "CNHSGwNEeGCXKf0bRgq98NqvWYrts227")
         val api: RetroInterface = RetroClient(credentials).api
         val response: NetworkResponse<GetUserProfile.Response, ErrorResponse> = api.getUserProfile(
            username = userName,
         )
         if (response is NetworkResponse.Success) {
            // handle the data
            val userProfile: GetUserProfile.Response = response.body
            Log.d(TAG, userProfile.user)
         } else if (response is NetworkResponse.Error) {
            // if the server returns an error it be found here
            val errorResponse: ErrorResponse? = response.body
            Log.d(TAG, errorResponse.toString())
            // if the api (locally) had an internal error, it'll be found here
            val internalError: Throwable? = response.error
            Log.d(TAG,internalError.toString())
         }
      }


   }
}