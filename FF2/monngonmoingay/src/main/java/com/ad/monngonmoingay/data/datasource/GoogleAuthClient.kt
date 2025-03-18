package com.ad.monngonmoingay.data.datasource

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.ad.monngonmoingay.R
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import java.io.IOException
import javax.inject.Inject

class GoogleAuthClient @Inject constructor(
) {

   val auth = FirebaseAuth.getInstance()
   private val tag = "GoogleAuthClient"

   private suspend fun buildCredentialRequest(context: Activity): GetCredentialResponse {

      val option = GetGoogleIdOption.Builder()
         .setFilterByAuthorizedAccounts(false)
         .setServerClientId(context.getString(R.string.client_id))
         .setAutoSelectEnabled(false)
         .build()

      val request = GetCredentialRequest.Builder()
         .addCredentialOption(option)
         .build()

      return CredentialManager.create(context).getCredential(
         request = request, context = context
      )
   }

   suspend fun signIn(context: Activity): Boolean {
      if (!isWifiConnected(context)) {
         throw IOException("No Wifi connection")
      }
      val result = buildCredentialRequest(context)
      return handleSingIn(result)
   }

   /**
    * Handles the sign-in process after receiving a credential response.
    *
    * This function attempts to authenticate the user with Firebase using a Google ID token
    * obtained from the provided credential response. It checks if the received credential is
    * a Google ID token credential, extracts the token, and uses it to sign in to Firebase.
    *
    * @param result The result of the credential retrieval process, containing the user's credential.
    * @return `true` if the sign-in was successful (a user was authenticated), `false` otherwise.
    */
   private suspend fun handleSingIn(result: GetCredentialResponse): Boolean {
      val credential = result.credential

      if (
         credential is CustomCredential &&
         credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
      ) {

         val tokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

         Log.d(tag, "name: ${tokenCredential.displayName}")
         Log.d(tag, "email: ${tokenCredential.id}")
         Log.d(tag, "image: ${tokenCredential.profilePictureUri}")

         val authCredential = GoogleAuthProvider.getCredential(
            tokenCredential.idToken, null
         )

         val authResult = auth.signInWithCredential(authCredential).await()

         return authResult.user != null
      } else {
         Log.d(tag, "credential is not GoogleIdTokenCredential")
         return false
      }

   }

   fun isWifiConnected(context: Context): Boolean {
      val connectivityManager =
         context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      val network = connectivityManager.activeNetwork
      val capabilities = connectivityManager.getNetworkCapabilities(network)
      return capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true
   }

}