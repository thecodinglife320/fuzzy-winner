package com.ad.ff3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ad.ff3.ui.theme.FF3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FF3Theme {
            }
        }
    }
}

@Composable
fun Basic(name: String, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            PacktSmallTopAppBar()
        },
        bottomBar = {
            PacktBottomNavigationBar()
        },
        floatingActionButton = {
            PacktFloatingActionButton()
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.Gray.copy(alpha = 0.1f)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Mastering Kotlin for Android Development -
                            Chapter 4",
                            textAlign = TextAlign.Center
                )
            }
        }
    )
}

@Composable
fun Basic1() {
    BottomAppBar(
        actions = {
            Icon(imageVector = Icons.Rounded.Home, contentDescription =
            "Home Screen")
            Icon(imageVector = Icons.Rounded.ShoppingCart,
                contentDescription = "Cart Screen")
            Icon(imageVector = Icons.Rounded.AccountCircle,
                contentDescription = "Account Screen")
        }
    )
}

@Composable
fun Layout() {
    ConstraintLayout(
        modifier = Modifier
            .padding(16.dp)
    ) {
        val (icon, text) = createRefs()
        Icon(
            modifier = Modifier
                .size(80.dp)
                .constrainAs(icon) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                },
            imageVector = Icons.Outlined.Notifications,
            contentDescription = null,
            tint = Color.Green
        )
        Text(
            text = "9",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .constrainAs(text){
                    top.linkTo(parent.top)
                    start.linkTo(icon.end)
                }
        )
    }
}

@Preview(showBackground = false)
@Composable
fun GreetingPreview() {
    FF3Theme {
        Basic1()
    }
}