package com.ad.ff2.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.ad.ff2.R

@Composable
fun HappyBirthday(message: String, from: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.androidparty)
    Box {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.5F
        )
        Column(modifier=modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center) {
            Text(
                text = "$message!",
                fontSize = 50.sp,
                lineHeight = 55.sp,
                textAlign = TextAlign.Start
            )
            Text(
                modifier = modifier
                    .align(Alignment.End),
                text = "from $from",
                fontSize = 22.sp,
            )
        }
    }
}