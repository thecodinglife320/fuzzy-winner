package com.ad.monngonmoingay.data.datasource

import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * Tiêm auth firebase vào constructor
 */
class AuthRemoteDataSource @Inject constructor(
   private val auth: FirebaseAuth
) {

   val currentUser: FirebaseUser?
      get() = auth.currentUser

   val currentUserId: Flow<String?>
      get() = callbackFlow {
         val listener = FirebaseAuth.AuthStateListener { _ ->
            this.trySend(currentUser?.uid)
         }
         auth.addAuthStateListener(listener)
         awaitClose { auth.removeAuthStateListener(listener) }
      }

   suspend fun createGuestAccount() {
      auth.signInAnonymously().await()
   }

   suspend fun signIn(email: String, password: String) {
      if (auth.currentUser!!.isAnonymous)
         auth.currentUser!!.delete()
      auth.signInWithEmailAndPassword(email, password).await()
   }

   suspend fun signUp(email: String, password: String) {
      val credential = EmailAuthProvider.getCredential(email, password)
      auth.currentUser!!.linkWithCredential(credential).await()
      signIn(email, password)
   }

   fun signOut() {
      if (auth.currentUser!!.isAnonymous) auth.currentUser!!.delete()
      auth.signOut()
   }

   //Khi người dùng đã có tài khoản
   suspend fun deleteAccount() {
      auth.currentUser!!.delete().await()
   }
}