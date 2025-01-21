package com.example.videoplayer.UI_layer

import android.annotation.SuppressLint
import androidx.annotation.OptIn
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.videoplayer.Data_layer.videoFile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class SharedViewModel():ViewModel() {


    private val _videoList= MutableStateFlow<List<videoFile>>(emptyList())
    var videoList=_videoList.asStateFlow()

    @OptIn(UnstableApi::class)
    @SuppressLint("Range")
    fun CollectVideoList(videos:List<videoFile>){
        Log.d("videos in sharedviewmodel:", videos.toString())
        _videoList.value=videos
        Log.d("_videoList.value sharedview",  _videoList.value.toString())
    }
}