package com.ad.ff2

import com.ad.ff2.composable.calculateTip1
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalTests {

   @Test
   fun calculateTip_20PercentNoRoundup() {
      val amount = 10.00
      val tipPercent = 20.00
      val expectedTip = NumberFormat.getCurrencyInstance().format(2)
      val actualTip = calculateTip1(amount = amount, tipPercent = tipPercent, false)
      assertEquals(expectedTip, actualTip)
   }
}