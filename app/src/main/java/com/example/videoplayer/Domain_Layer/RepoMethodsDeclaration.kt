package com.example.videoplayer.Domain_Layer

import android.app.Application
import com.example.videoplayer.Data_layer.videoFile
import kotlinx.coroutines.flow.Flow


interface RepoMethodsDeclaration {
    suspend fun getAllVideos(application: Application): Flow<List<videoFile>>

    suspend fun videoByFolder(application: Application):Flow<Map<String,List<videoFile>>>
}