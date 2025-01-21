package com.example.videoplayer.UI_layer.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.media3.common.MediaItem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.util.fastCbrt
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@SuppressLint("RememberReturnType")
@Composable
fun PlayerScreen(
     Url:String
){
     val context= LocalContext.current
     val exoPlayer=remember{
          ExoPlayer.Builder(context).build().apply {
             setMediaItem(MediaItem.fromUri(Url))
               prepare()
               playWhenReady=true
               play()
          }
     }
     Column {
        DisposableEffect(
             AndroidView(
                  factory = {
                      PlayerView(context).apply{
                           player=exoPlayer
                      }
                  },
                  update = {
                      it.player=exoPlayer
                  }
        )) {
             onDispose {
                  exoPlayer.release()
             }

        }
     }

}