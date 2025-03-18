package com.ad.monngonmoingay.data.repository

import android.app.Activity
import com.ad.monngonmoingay.data.datasource.AuthRemoteDataSource
import com.ad.monngonmoingay.data.datasource.GoogleAuthClient
import javax.inject.Inject

/**
 * Tiêm authRemoteDataSource vào constructor
 */
class AuthRepository @Inject constructor(
   private val authRemoteDataSource: AuthRemoteDataSource,
   private val googleAuthClient: GoogleAuthClient
) {
   val currentUserIdFlow = authRemoteDataSource.currentUserId
   val currentUser
      get() = authRemoteDataSource.currentUser

   suspend fun createGuestAccount() = authRemoteDataSource.createGuestAccount()

   suspend fun signIn(email: String, password: String) {
      authRemoteDataSource.signIn(email, password)
   }

   suspend fun signInWithGoogle(context: Activity) = googleAuthClient.signIn(context = context)

   fun signOut() = authRemoteDataSource.signOut()

   suspend fun deleteAccount() = authRemoteDataSource.deleteAccount()

   suspend fun signUp(email: String, password: String) =
      authRemoteDataSource.signUp(email, password)
}