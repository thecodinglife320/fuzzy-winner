package com.ad.tipofwellness.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ad.tipofwellness.R

val UbuntuMono = FontFamily(
   Font(R.font.ubuntu_mono),
)

val FiraCode = FontFamily(
   Font(R.font.fira_code_light),

)

val AppTypography = Typography(
   titleLarge = TextStyle(
      fontFamily = UbuntuMono,
      fontWeight = FontWeight.Normal,
      fontSize = 22.sp,
      lineHeight = 24.sp,
      letterSpacing = 0.sp
   ),
   bodyLarge = TextStyle(
      fontFamily = FiraCode,
      fontWeight = FontWeight.Normal,
      fontSize = 16.sp,
      lineHeight = 24.sp,
      letterSpacing = 0.sp
   )
)

