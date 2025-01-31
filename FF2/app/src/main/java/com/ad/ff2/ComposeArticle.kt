package com.ad.ff2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ad.ff2.ui.theme.FF2Theme

class ComposeArticle : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FF2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    ComposeQuadrant()
                }
            }
        }
    }
}

@Composable
fun Article(title: String, body1: String, body2: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.bg_compose_background)
    Column(modifier = modifier) {
        Image(
            painter = image,
            contentDescription = null
        )
        Text(
            text = title,
            modifier = Modifier.padding(16.dp),
            fontSize = 24.sp
        )
        Text(
            text = body1,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
            ),
            textAlign = TextAlign.Justify
        )
        Text(
            text = body2,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun AllTasksCompleted(modifier: Modifier=Modifier) {
    Column(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(R.drawable.ic_task_completed),
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.task_complete),
            modifier = modifier.padding(top = 24.dp, bottom = 8.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(R.string.nice_work),
            fontSize = 16.sp
        )
    }
}

@Composable
fun ComposeQuadrant() {
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.weight(2f)) {
            ComposableInfoCard(
                title = stringResource(R.string.title1),
                description = stringResource(R.string.title1_desc),
                backgroundColor = colorResource(R.color.c4),
                modifier = Modifier.weight(2f)
            )
            ComposableInfoCard(
                title = stringResource(R.string.title2),
                description = stringResource(R.string.title2_desc),
                backgroundColor = colorResource(R.color.c1),
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            ComposableInfoCard(
                title = stringResource(R.string.title3),
                description = stringResource(R.string.title3_desc),
                backgroundColor = colorResource(R.color.c2),
                modifier = Modifier.weight(1f)
            )
            ComposableInfoCard(
                title = stringResource(R.string.title4),
                description = stringResource(R.string.title4_desc),
                backgroundColor = colorResource(R.color.c3),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ComposableInfoCard(title: String, description: String, backgroundColor: Color, modifier: Modifier=Modifier){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = 8.dp,
                top = 8.dp
            )
            .background(color = backgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        // Title text
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        // Description text
        Text(
            textAlign = TextAlign.Justify,
            text = description,
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true)
@Composable
fun ArticlePreview() {
    FF2Theme {
        //AllTasksCompleted(modifier = Modifier)
        ComposeQuadrant()
        //ComposableInfoCard(stringResource(R.string.title1), stringResource(R.string.title1_desc), colorResource(R.color.c1),modifier = Modifier)
    }
}