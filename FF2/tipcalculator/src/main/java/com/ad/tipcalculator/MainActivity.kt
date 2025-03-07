package com.ad.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ad.tipcalculator.ui.theme.AppTheme
import java.text.NumberFormat
import java.util.Locale

class MainActivity : ComponentActivity() {
   @OptIn(ExperimentalMaterial3Api::class)
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      setContent {
         AppTheme {
            Scaffold(
               topBar = {
                  CenterAlignedTopAppBar(
                     title = {
                        Text(text = stringResource(id = R.string.app_name))
                     },
                  )
               },
               modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
               TipTimeLayout(
                  calcTip = { amount, tipPercent, roundUp ->
                     calculateTip1(amount, tipPercent, roundUp)
                  },
                  Modifier.padding(innerPadding)
               )
            }
         }
      }
   }
}

private fun roundToNearestThousand(amount: Int): Int {
   return ((amount + 500) / 1000) * 1000
}

@VisibleForTesting
internal fun calculateTip1(amount: Double, tipPercent: Double = 15.0, roundUp: Boolean): String {
   var tip = tipPercent / 100 * amount
   if (roundUp) {
      tip = kotlin.math.ceil(tip)
   }
   return NumberFormat.getCurrencyInstance(Locale.US).format(tip)
}

@VisibleForTesting
internal fun calculateTip(
   amount: Double,
   tipPercent: Double = 15.0,
   roundUp: Boolean
): String {
   var tip = (tipPercent / 100 * amount).toInt()
   if (roundUp) tip = roundToNearestThousand(tip)
   return NumberFormat.getCurrencyInstance(Locale("vi", "VN")).format(tip)
}
