package com.ad.monngonmoingay.data.repository

import com.ad.monngonmoingay.data.datasource.AuthRemoteDataSource
import javax.inject.Inject

/**
 * Tiêm authRemoteDataSource vào constructor
 */
class AuthRepository @Inject constructor(
   private val authRemoteDataSource: AuthRemoteDataSource
) {
   val currentUserIdFlow = authRemoteDataSource.currentUserIdFlow
   val currentUser = authRemoteDataSource.currentUser

   suspend fun createGuestAccount() = authRemoteDataSource.createGuestAccount()

   suspend fun signIn(email: String, password: String) =
      authRemoteDataSource.signIn(email, password)

   fun signOut() = authRemoteDataSource.signOut()

   suspend fun deleteAccount() = authRemoteDataSource.deleteAccount()

   suspend fun signUp(email: String, password: String) =
      authRemoteDataSource.linkAccount(email, password)
}