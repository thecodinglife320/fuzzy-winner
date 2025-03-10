package com.ad.monngonmoingay.ui.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ad.monngonmoingay.R
import com.ad.monngonmoingay.ui.shared.StandardButton
import com.ad.monngonmoingay.ui.theme.AppTheme

object SettingDestination {
   const val ROUTE = "settings"
}

@Composable
fun SettingsScreen(
   openHomeScreen: () -> Unit,
   openSignInScreen: () -> Unit,
   viewModel: SettingsViewModel = hiltViewModel()
) {

   val shouldRestartApp by viewModel.shouldRestartApp.collectAsStateWithLifecycle()

   if (shouldRestartApp) {
      openHomeScreen()
   } else {
      val isAnonymous by viewModel.isAnonymousOrSignOut.collectAsStateWithLifecycle()

      SettingsScreenContent(
         loadCurrentUser = viewModel::loadCurrentUser,
         openSignInScreen = openSignInScreen,
         signOut = viewModel::signOut,
         deleteAccount = viewModel::deleteAccount,
         isAnonymous = isAnonymous
      )
   }
}

@Composable
fun SettingsScreenContent(
   loadCurrentUser: () -> Unit,
   openSignInScreen: () -> Unit,
   signOut: () -> Unit,
   deleteAccount: () -> Unit,
   isAnonymous: Boolean
) {

   LaunchedEffect(true) {
      loadCurrentUser()
   }
   Column(
      modifier = Modifier
         .fillMaxSize()
         .padding(dimensionResource(R.dimen.small_pading))
   ) {
      Spacer(Modifier.size(24.dp))

      if (isAnonymous) {
         StandardButton(
            label = R.string.sign_in,
            onButtonClick = {
               openSignInScreen()
            }
         )
      } else {
         StandardButton(
            label = R.string.sign_out,
            onButtonClick = {
               signOut()
            }
         )

         Spacer(Modifier.size(16.dp))

         DeleteAccountButton(deleteAccount)
      }
   }
}

@Composable
fun DeleteAccountButton(deleteAccount: () -> Unit) {
   var showDeleteAccountDialog by remember { mutableStateOf(false) }

   StandardButton(
      label = R.string.delete_account,
      onButtonClick = {
         showDeleteAccountDialog = true
      }
   )

   if (showDeleteAccountDialog) {
      AlertDialog(
         title = { Text(stringResource(R.string.delete_account_title)) },
         text = { Text(stringResource(R.string.delete_account_description)) },
         dismissButton = {
            TextButton(
               onClick = { showDeleteAccountDialog = false },
            ) {
               Text(text = stringResource(R.string.cancel), fontSize = 16.sp)
            }
         },
         confirmButton = {
            TextButton(
               onClick = {
                  showDeleteAccountDialog = false
                  deleteAccount()
               },
            ) {
               Text(text = stringResource(R.string.delete), fontSize = 16.sp)
            }
         },
         onDismissRequest = { showDeleteAccountDialog = false }
      )
   }
}

@Composable
@Preview(showSystemUi = true)
fun SettingsScreenPreview() {
   AppTheme(darkTheme = true) {
      SettingsScreenContent(
         loadCurrentUser = {},
         openSignInScreen = {},
         signOut = {},
         deleteAccount = {},
         isAnonymous = false
      )
   }
}