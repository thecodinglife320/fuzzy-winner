package com.learning.ad.ff1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RepoViewModel : ViewModel() {
   private val gitHubApi = GitHubService.gitHubAPI
   private val _repos = MutableLiveData<List<Repo>>()
   private val _userID= MutableLiveData("nemisolv")
   private val userID: LiveData<String> get() = _userID
   private val repos: LiveData<List<Repo>> get() = _repos

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
         } catch (e: Exception) {
            println("Error fetching repos: ${e.message}")
         }
      }
   }
}