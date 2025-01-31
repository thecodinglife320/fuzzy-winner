package com.ad.ff2

 import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ad.ff2.ui.theme.FF2Theme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FF2Theme {
                GreetingImage(
                    message = stringResource(R.string.happy_birthday_dat),
                    from = stringResource(R.string.emma),
                )
            }
        }
    }
}

@Composable
fun GreetingText(message: String, from: String,modifier: Modifier=Modifier) {
    Column(modifier=modifier
        .fillMaxSize()
        .padding(start = 10.dp),
        verticalArrangement = Arrangement.Center) {
        Text(
            modifier = modifier.padding(start = 10.dp),
            text = "$message!",
            fontSize = 50.sp,
            lineHeight = 55.sp,
            textAlign = TextAlign.Start
        )
        Text(
            modifier = modifier.padding(start = 10.dp)
                .align(Alignment.End),
            text = "from $from",
            fontSize = 22.sp,
        )
    }
}

@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.androidparty)
    Box(modifier) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.5F
        )
        GreetingText(
            message = message,
            from = from
        )
    }
}

@Preview(showBackground = true,showSystemUi = true)
@Composable
fun GreetingPreview() {
    FF2Theme {
        GreetingImage(message = "Happy Birthday Dat!", from = "Emma")
    }
}