package com.example.videoplayer

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.videoplayer.Data_layer.videoFile

data class VideoFolderStates(
    var folders:MutableState<Map<String, List<videoFile>>> = mutableStateOf(emptyMap()),
    var isLoading:MutableState<Boolean> = mutableStateOf(true)
)
