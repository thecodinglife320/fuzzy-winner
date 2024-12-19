package com.learning.ad.ff1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.ad.ff1.network.APIService
import com.learning.ad.ff1.network.Repo
import kotlinx.coroutines.launch

class RepoViewModel : ViewModel() {
   private val gitHubApi = APIService.gitHubAPI
   private val _repos = MutableLiveData<List<Repo>>()
   private val _userID= MutableLiveData("nemisolv")
   val userID: LiveData<String> get() = _userID
   val repos: LiveData<List<Repo>> get() = _repos

   init {
      fetchRepos()
   }
   private fun fetchRepos() {
      viewModelScope.launch {
         try {
            _repos.value = gitHubApi.getRepos("users/${userID.value}/repos")
            repos.value?.forEach { repo ->
               println(repo)
            }
            println(1 multiplyBy 2)
         } catch (e: Exception) {
            println("Error fetching repos: ${e.message}")
         }
      }
   }

   infix fun Int.multiplyBy(x: Int): Int = this*x
}