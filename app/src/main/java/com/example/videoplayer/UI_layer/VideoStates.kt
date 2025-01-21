package com.example.videoplayer.UI_layer

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.videoplayer.Data_layer.videoFile

data class VideoStates(
    var allVideos:List<videoFile> = emptyList(),
    var isLoading:MutableState<Boolean> = mutableStateOf(true)
)
