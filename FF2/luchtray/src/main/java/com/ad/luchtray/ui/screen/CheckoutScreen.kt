/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ad.luchtray.ui.screen

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.ad.luchtray.R
import com.ad.luchtray.datasource.DataSource
import com.ad.luchtray.model.MenuItem
import com.ad.luchtray.model.OrderUiState
import com.ad.luchtray.ui.stateholder.formatPrice
import com.ad.luchtray.ui.theme.AppTheme

@Composable
fun CheckoutScreen(
   orderUiState: OrderUiState,
   onNextButtonClicked: () -> Unit,
   onCancelButtonClicked: () -> Unit,
   modifier: Modifier = Modifier
) {
   Column(
      modifier = modifier,
      verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
   ) {
      Text(
         text = stringResource(R.string.order_summary),
         fontWeight = FontWeight.Bold,
         style = MaterialTheme.typography.titleLarge
      )
      ItemSummary(item = orderUiState.entree, modifier = Modifier.fillMaxWidth())
      ItemSummary(item = orderUiState.sideDish, modifier = Modifier.fillMaxWidth())
      ItemSummary(item = orderUiState.accompaniment, modifier = Modifier.fillMaxWidth())

      HorizontalDivider(
         modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_small)),
         thickness = dimensionResource(R.dimen.thickness_divider)
      )

      OrderSubCost(
         resourceId = R.string.subtotal,
         price = orderUiState.itemTotalPrice.formatPrice(),
         Modifier.align(Alignment.End)
      )

      OrderSubCost(
         resourceId = R.string.tax,
         price = orderUiState.orderTax.formatPrice(),
         Modifier.align(Alignment.End)
      )

      Text(
         text = stringResource(R.string.total, orderUiState.orderTotalPrice.formatPrice()),
         modifier = Modifier.align(Alignment.End),
         fontWeight = FontWeight.Bold
      )

      Row(
         modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_medium)),
         horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
      ) {
         OutlinedButton(modifier = Modifier.weight(1f), onClick = onCancelButtonClicked) {
            Text(stringResource(R.string.cancel).uppercase())
         }
         Button(
            modifier = Modifier.weight(1f),
            onClick = onNextButtonClicked
         ) {
            Text(stringResource(R.string.submit).uppercase())
         }
      }
   }
}

@Composable
fun ItemSummary(
   item: MenuItem?,
   modifier: Modifier = Modifier
) {
   Row(
      modifier = modifier,
      horizontalArrangement = Arrangement.SpaceBetween
   ) {
      Text(
         item?.name ?: "",
         color = MaterialTheme.colorScheme.primary,
         style = MaterialTheme.typography.bodyLarge
      )
      Text(
         item?.getFormattedPrice() ?: "",
         style = MaterialTheme.typography.bodyLarge
      )
   }
}

@Composable
fun OrderSubCost(
   @StringRes resourceId: Int,
   price: String,
   modifier: Modifier = Modifier
) {
   Text(
      text = stringResource(resourceId, price),
      modifier = modifier
   )
}

@Preview(showSystemUi = true, device = "id:4.7in WXGA")
@Composable
fun CheckoutScreenPreview() {
   AppTheme(darkTheme = false) {
      CheckoutScreen(
         orderUiState = OrderUiState(
            entree = DataSource.entreeMenuItems[0],
            sideDish = DataSource.sideDishMenuItems[0],
            accompaniment = DataSource.accompanimentMenuItems[0],
            itemTotalPrice = 15.00,
            orderTax = 1.00,
            orderTotalPrice = 16.00
         ),
         onNextButtonClicked = {},
         onCancelButtonClicked = {},
         modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_medium))
            .verticalScroll(rememberScrollState())
      )
   }
}
