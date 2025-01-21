package com.example.videoplayer.UI_layer.Navigation

import com.example.videoplayer.Data_layer.videoFile
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
object videoscreen

@Serializable
object homeScreen

@Serializable
object fileScreen

@Serializable
data class playerScreen(
    val path:String
)

@Serializable
object videosByFile
