package com.ad.monngonmoingay.ui.shared

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.ad.monngonmoingay.R
import com.ad.monngonmoingay.ui.theme.DarkGrey
import com.ad.monngonmoingay.ui.theme.FF2Theme
import com.ad.monngonmoingay.ui.theme.LightYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterTopAppBar(
   title: String,
   icon: ImageVector,
   iconDescription: String,
   action: () -> Unit,
   scrollBehavior: TopAppBarScrollBehavior
) {
   CenterAlignedTopAppBar(
      colors = appBarColors(),
      title = {
         Text(
            title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
         )
      },
      actions = {
         IconButton(onClick = { action() }) {
            Icon(
               imageVector = icon,
               contentDescription = iconDescription
            )
         }
      },
      scrollBehavior = scrollBehavior
   )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterTopAppBar(
   title: String,
   scrollBehavior: TopAppBarScrollBehavior
) {
   CenterAlignedTopAppBar(
      colors = appBarColors(),
      title = {
         Text(
            title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
         )
      },
      scrollBehavior = scrollBehavior
   )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun CenterTopAppBarPreview() {
   FF2Theme(darkTheme = false) {
      CenterTopAppBar(
         title = "Center Top AppBar",
         icon = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground),
         iconDescription = "back",
         action = {},
         scrollBehavior = pinnedScrollBehavior()
      )
   }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun appBarColors(): TopAppBarColors {
   return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
      TopAppBarDefaults.centerAlignedTopAppBarColors()
   } else {
      TopAppBarDefaults.centerAlignedTopAppBarColors(
         containerColor = if (isSystemInDarkTheme()) DarkGrey else LightYellow
      )
   }
}

