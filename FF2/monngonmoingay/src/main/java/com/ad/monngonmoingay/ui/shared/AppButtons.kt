package com.ad.monngonmoingay.ui.shared

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ad.monngonmoingay.ui.theme.DarkBlue

@Composable
fun StandardButton(@StringRes label: Int, onButtonClick: () -> Unit) {
   OutlinedButton(
      modifier = Modifier
         .fillMaxWidth()
         .padding(horizontal = 24.dp),
      onClick = onButtonClick,
      colors = ButtonDefaults.outlinedButtonColors(
         containerColor = DarkBlue,
         contentColor = Color.White
      ),
      border = BorderStroke(1.dp, DarkBlue)
   ) {
      Text(
         text = stringResource(label),
         fontSize = 16.sp,
         modifier = Modifier.padding(vertical = 6.dp)
      )
   }
}