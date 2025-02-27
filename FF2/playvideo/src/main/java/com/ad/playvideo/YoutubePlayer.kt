package com.ad.playvideo

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.customui.DefaultPlayerUiController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YoutubePlayer(
   youtubeVideoId: String,
   lifecycleOwner: LifecycleOwner,
   modifier: Modifier = Modifier
) {

   AndroidView(
      modifier = modifier
         .fillMaxWidth()
         .clip(RoundedCornerShape(16.dp)),
      factory = { context ->
         YouTubePlayerView(
            context = context
         ).apply {

            lifecycleOwner.lifecycle.addObserver(this)

            val listener = object : AbstractYouTubePlayerListener() {
               override fun onReady(youTubePlayer: YouTubePlayer) {
                  youTubePlayer.loadVideo(youtubeVideoId, 0f)
               }

            }

            enableAutomaticInitialization = false

            // disable web ui
            val options: IFramePlayerOptions = IFramePlayerOptions.Builder().controls(0).build()
            val youTubePlayerView = this

            val youTubePlayerCallback = object : YouTubePlayerCallback {
               override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                  println("YouTube Player is ready: $youTubePlayer")
                  val defaultPlayerUiController =
                     DefaultPlayerUiController(youTubePlayerView, youTubePlayer)
                  setCustomPlayerUi(defaultPlayerUiController.rootView)
               }
            }

            getYouTubePlayerWhenReady(
               youTubePlayerCallback = youTubePlayerCallback
            )

            initialize(listener, options)
         }
      })
}