package com.ad.ff2

 import android.annotation.SuppressLint
 import android.content.res.Configuration
 import android.os.Bundle
 import androidx.activity.ComponentActivity
 import androidx.activity.compose.setContent
 import androidx.activity.enableEdgeToEdge
 import androidx.compose.runtime.Composable
 import androidx.compose.ui.tooling.preview.Preview
 import androidx.compose.ui.tooling.preview.Wallpapers
 import com.ad.ff2.composable.ComposeQuadrant
 import com.ad.ff2.ui.theme.FF2Theme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FF2Theme {
                ComposeQuadrant()
            }
        }
    }
}

@Preview(
    showSystemUi = true, wallpaper = Wallpapers.NONE,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true, apiLevel = 31, device = "id:4.65in 720p (Galaxy Nexus)"
)
@Composable
fun Preview() {
    FF2Theme {
        ComposeQuadrant()
    }
}