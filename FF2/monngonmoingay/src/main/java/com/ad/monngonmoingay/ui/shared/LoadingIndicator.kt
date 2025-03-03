package com.ad.monngonmoingay.ui.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ad.monngonmoingay.ui.theme.DarkBlue
import com.ad.monngonmoingay.ui.theme.LightYellow

@Composable
fun LoadingIndicator() {
   Box(modifier = Modifier.fillMaxSize()) {

      CircularProgressIndicator(
         modifier = Modifier
            .width(64.dp)
            .align(Alignment.Center),
         color = DarkBlue,
         trackColor = LightYellow,
      )
   }
}