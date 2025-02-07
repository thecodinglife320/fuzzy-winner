package com.ad.ff2

 import android.annotation.SuppressLint
 import android.os.Bundle
 import androidx.activity.ComponentActivity
 import androidx.activity.compose.setContent
 import androidx.activity.enableEdgeToEdge
 import com.ad.ff2.gridcourse.TopicCourseLayout
 import com.ad.ff2.ui.theme.FF2Theme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FF2Theme {
               TopicCourseLayout()
            }
        }
    }
}