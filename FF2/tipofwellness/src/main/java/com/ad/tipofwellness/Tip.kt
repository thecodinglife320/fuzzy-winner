package com.ad.tipofwellness

data class Tip(
   val nameRes: Int,
   val descriptionRes: Int,
   val imageRes: Int,
   val day: Int
)

val tips = listOf(
   Tip(R.string.tip1_title, R.string.tip1_desc, R.drawable.pic1, 1),
   Tip(R.string.tip2_title, R.string.tip2_desc, R.drawable.pic2, 2),
   Tip(R.string.tip3_title, R.string.tip3_desc, R.drawable.pic3, 3),
   Tip(R.string.tip4_title, R.string.tip4_desc, R.drawable.pic4, 4),
   Tip(R.string.tip5_title, R.string.tip5_desc, R.drawable.pic5, 5),
)
