package com.ad.monngonmoingay.ui.login

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ad.monngonmoingay.R
import com.ad.monngonmoingay.data.model.ErrorMessage
import com.ad.monngonmoingay.ui.shared.StandardButton
import com.ad.monngonmoingay.ui.theme.AppTheme

@Composable
fun SignInScreen(
   restartApp: () -> Unit,
   openSignUpScreen: () -> Unit,
   showErrorSnackBar: (ErrorMessage) -> Unit,
   viewModel: SignInViewModel = hiltViewModel()
) {
   val shouldRestartApp by viewModel.shouldRestartApp.collectAsStateWithLifecycle()

   if (shouldRestartApp)
      restartApp()
   else SignInScreenContent(
      openSignUpScreen = openSignUpScreen,
      signIn = viewModel::signIn,
      signInWithGoogle = viewModel::signInWithGoogle,
      showErrorSnackBar = showErrorSnackBar
   )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SignInScreenContent(
   openSignUpScreen: () -> Unit,
   signIn: (String, String, (ErrorMessage) -> Unit) -> Unit,
   signInWithGoogle: ((ErrorMessage) -> Unit, context: Activity) -> Unit,
   showErrorSnackBar: (ErrorMessage) -> Unit
) {

   var email by remember { mutableStateOf("") }
   var password by remember { mutableStateOf("") }
   val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
   val context = LocalContext.current

   Scaffold(
      modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
   ) { innerPadding ->
      ConstraintLayout(
         modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
      ) {
         val (appLogo,
            form,
            signUpText
         ) = createRefs()

         //image
         Column(
            modifier = Modifier
               .constrainAs(appLogo) {
                  top.linkTo(parent.top)
                  start.linkTo(parent.start)
                  end.linkTo(parent.end)
               },
            horizontalAlignment = Alignment.CenterHorizontally
         ) {

            Image(
               modifier = Modifier.size(100.dp),
               painter = painterResource(id = R.drawable.ic_launcher_foreground),
               contentDescription = "App logo"
            )

         }

         //form
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
               onValueChange = { userInput -> email = userInput },
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
               visualTransformation = PasswordVisualTransformation(),
               keyboardOptions = KeyboardOptions.Default.copy(
                  imeAction = ImeAction.Go
               )
            )

            Spacer(Modifier.size(32.dp))

            StandardButton(
               label = R.string.sign_in_with_email,
               onButtonClick = {
                  signIn(email, password, showErrorSnackBar)
               }
            )

            Spacer(Modifier.size(16.dp))

            Text("or sign in with")

            Spacer(Modifier.size(8.dp))

            Row {
               Image(
                  painter = painterResource(id = R.drawable.google_icon),
                  contentDescription = "google icon",
                  modifier = Modifier
                     .size(40.dp)
                     .clickable {
                        signInWithGoogle(showErrorSnackBar, context as Activity)
                     }
               )
               Spacer(Modifier.size(16.dp))
               Image(
                  painter = painterResource(id = R.drawable.facebook_icon),
                  contentDescription = "facebook icon",
                  modifier = Modifier
                     .size(40.dp)
                     .clickable {

                     }
               )

            }

         }

         //sign up
         Column(
            modifier = Modifier
               .constrainAs(signUpText) {
                  bottom.linkTo(parent.bottom)
                  start.linkTo(parent.start)
                  end.linkTo(parent.end)
               },
            horizontalAlignment = Alignment.CenterHorizontally
         ) {
            TextButton(onClick = openSignUpScreen) {
               Text(
                  text = stringResource(R.string.sign_up_text),
                  textAlign = TextAlign.Center,
                  fontSize = 16.sp,
               )
            }

            Spacer(Modifier.size(24.dp))
         }
      }
   }
}

@Composable
@Preview(showSystemUi = true, device = "id:4.7in WXGA")
fun SignInScreenPreview() {
   AppTheme(darkTheme = false) {
      SignInScreenContent(
         openSignUpScreen = {},
         signIn = { _, _, _ -> },
         signInWithGoogle = { _, _ -> },
         showErrorSnackBar = {}
      )
   }
}