package com.ad.ff2.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ad.ff2.R
import java.text.NumberFormat
import java.util.Locale

@Composable
fun TipTimeLayout() {

   // UI element state
   var amountInput by remember { mutableStateOf("") }
   var tipInput by remember { mutableStateOf("") }
   var roundUp by remember { mutableStateOf(false) }

   // Screen UI state
   val amount = amountInput.toDoubleOrNull() ?: 0.0
   val tipPercent = tipInput.toDoubleOrNull() ?: 0.0
   val tip = calculateTip(amount, tipPercent, roundUp)

   Column(
      modifier = Modifier
         .statusBarsPadding()
         .padding(horizontal = 20.dp)
         .safeDrawingPadding()
         .verticalScroll(rememberScrollState()),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
   ) {
      Text(
         text = stringResource(R.string.calculate_tip),
         modifier = Modifier
            .padding(bottom = 16.dp, top = 40.dp)
            .align(alignment = Alignment.Start)
      )
      EditNumberField(
         label = R.string.bill_amount,
         value = amountInput,
         onValueChange = { amountInput = it },
         modifier = Modifier
            .padding(bottom = 32.dp)
            .fillMaxWidth(),
         keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
         ),
         leadingIcon = R.drawable.money
      )

      EditNumberField(
         leadingIcon = R.drawable.percent,
         label = R.string.how_was_the_service,
         value = tipInput,
         onValueChange = { tipInput = it },
         modifier = Modifier
            .padding(bottom = 32.dp)
            .fillMaxWidth(),
         keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
         )
      )

      RoundTheTipRow(
         roundUp = roundUp,
         onRoundUpChanged = { roundUp = it },
      )

      Text(
         text = stringResource(R.string.tip_amount, tip),
         style = MaterialTheme.typography.displaySmall
      )
      Spacer(modifier = Modifier.height(150.dp))
   }
}

/**
 * Calculates the tip based on the user input and format the tip amount
 * according to the local currency.
 * Example would be "$10.00".
 */
private fun calculateTip(
   amount: Double,
   tipPercent: Double = 15.0,
   roundUp: Boolean
): String {
   var tip = (tipPercent / 100 * amount).toInt()
   if (roundUp) tip = roundToNearestThousand(tip)
   return NumberFormat.getCurrencyInstance(Locale("vi", "VN")).format(tip)
}

@Composable
fun EditNumberField(
   keyboardOptions: KeyboardOptions,
   @StringRes label: Int,
   @DrawableRes leadingIcon: Int,
   value: String,
   onValueChange: (String) -> Unit,
   modifier: Modifier = Modifier
) {
   TextField(
      leadingIcon = { Icon(painter = painterResource(id = leadingIcon), null) },
      value = value,
      keyboardOptions = keyboardOptions,
      //trigger recomposition when the state amountInput changes.
      onValueChange = onValueChange,
      modifier = modifier,
      singleLine = true,
      label = { Text(stringResource(label)) },
   )
}

@Composable
fun RoundTheTipRow(
   roundUp: Boolean,
   onRoundUpChanged: (Boolean) -> Unit,
   modifier: Modifier = Modifier
) {
   Row(
      modifier = modifier
         .fillMaxWidth()
         .size(60.dp),
      verticalAlignment = Alignment.CenterVertically
   ) {
      Text(text = stringResource(R.string.round_up_tip))
      Switch(
         checked = roundUp,
         onCheckedChange = onRoundUpChanged,
         modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End)
      )
   }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:4.7in WXGA")
@Composable
fun TipTimeLayoutPreview() {
   TipTimeLayout()
}

private fun roundToNearestThousand(amount: Int): Int {
   return ((amount + 500) / 1000) * 1000
}