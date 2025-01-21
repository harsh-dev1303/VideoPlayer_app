package com.example.videoplayer.Data_layer

import kotlinx.serialization.Serializable


data class videoFile(
    val id:String?,
    val path:String,
    val title:String?,
    val fileName:String,
    val dateAdded:String,
    val size:String?,
    val duration:String?,
    val thumbnail:String?=null
)
