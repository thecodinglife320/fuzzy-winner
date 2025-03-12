package com.ad.monngonmoingay.ui.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ad.monngonmoingay.R
import com.ad.monngonmoingay.data.model.ErrorMessage
import com.ad.monngonmoingay.ui.shared.StandardButton
import com.ad.monngonmoingay.ui.theme.AppTheme

@Suppress("ConstPropertyName")
object SignUpDestination {
   const val route = "SignUpScreen"
}

@Composable
fun SignUpScreen(
   restartApp: () -> Unit,
   showErrorSnackBar: (ErrorMessage) -> Unit,
   viewModel: SignUpViewModel = hiltViewModel()
) {
   val shouldRestartApp by viewModel.shouldRestartApp.collectAsStateWithLifecycle()

   if (shouldRestartApp) {
      restartApp()
   } else {
      SignUpScreenContent(
         signUp = viewModel::signUp,
         showErrorSnackBar = showErrorSnackBar
      )
   }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SignUpScreenContent(
   signUp: (String, String, String, (ErrorMessage) -> Unit) -> Unit,
   showErrorSnackBar: (ErrorMessage) -> Unit
) {
   var email by remember { mutableStateOf("") }
   var password by remember { mutableStateOf("") }
   var repeatPassword by remember { mutableStateOf("") }
   val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

   Scaffold(
      modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
   ) { innerPadding ->
      ConstraintLayout(modifier = Modifier
         .fillMaxSize()
         .padding(innerPadding)) {
         val (appLogo, form) = createRefs()

         Column(
            modifier = Modifier
               .constrainAs(appLogo) {
                  top.linkTo(parent.top)
                  start.linkTo(parent.start)
                  end.linkTo(parent.end)
               },
            horizontalAlignment = Alignment.CenterHorizontally
         ) {
            Spacer(Modifier.size(24.dp))

            Image(
               modifier = Modifier.size(88.dp),
               painter = painterResource(id = R.drawable.ic_launcher_foreground),
               contentDescription = "App logo"
            )

            Spacer(Modifier.size(24.dp))
         }

         Column(
            modifier = Modifier
               .constrainAs(form) {
                  top.linkTo(appLogo.bottom)
                  start.linkTo(parent.start)
                  end.linkTo(parent.end)
               },
            horizontalAlignment = Alignment.CenterHorizontally
         ) {
            Spacer(Modifier.size(24.dp))

            OutlinedTextField(
               modifier = Modifier
                  .fillMaxWidth()
                  .padding(horizontal = 24.dp),
               value = email,
               onValueChange = { email = it },
               label = { Text(stringResource(R.string.email)) },
               keyboardOptions = KeyboardOptions.Default.copy(
                  imeAction = ImeAction.Next
               )
            )

            Spacer(Modifier.size(16.dp))

            OutlinedTextField(
               modifier = Modifier
                  .fillMaxWidth()
                  .padding(horizontal = 24.dp),
               value = password,
               onValueChange = { password = it },
               label = { Text(stringResource(R.string.password)) },
               //visualTransformation = PasswordVisualTransformation(),
               keyboardOptions = KeyboardOptions.Default.copy(
                  imeAction = ImeAction.Next
               )
            )

            Spacer(Modifier.size(16.dp))

            OutlinedTextField(
               modifier = Modifier
                  .fillMaxWidth()
                  .padding(horizontal = 24.dp),
               value = repeatPassword,
               onValueChange = { repeatPassword = it },
               label = { Text(stringResource(R.string.repeat_password)) },
               //visualTransformation = PasswordVisualTransformation(),
               keyboardOptions = KeyboardOptions.Default.copy(
                  imeAction = ImeAction.Done
               )
            )

            Spacer(Modifier.size(32.dp))

            StandardButton(
               label = R.string.sign_up_with_email,
               onButtonClick = {
                  signUp(
                     email,
                     password,
                     repeatPassword,
                     showErrorSnackBar
                  )
               }
            )

            Spacer(Modifier.size(16.dp))

            //TODO: Uncomment line below when Google Authentication is implemented
            //AuthWithGoogleButton(R.string.sign_up_with_google) { }
         }
      }
   }
}

@Composable
@Preview(showSystemUi = true)
fun SignUpScreenPreview() {
   AppTheme(darkTheme = false) {
      SignUpScreenContent(
         signUp = { _, _, _, _ -> },
         showErrorSnackBar = {}
      )
   }
}