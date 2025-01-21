package com.example.videoplayer.UI_layer.ViewModel

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoplayer.Data_layer.Repo.myRepo
import com.example.videoplayer.Data_layer.videoFile
import com.example.videoplayer.UI_layer.VideoStates
import com.example.videoplayer.VideoFolderStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class MyViewModel @Inject constructor( val repo:myRepo,val application: Application ):ViewModel() {
    //val Videos=MutableStateFlow<List<videoFile>>(emptyList())   //yeh variable FileScreenUi se
     private var _states= MutableStateFlow(VideoStates())
     var AllVideostates=_states.asStateFlow()
     @SuppressLint("SuspiciousIndentation")
     @RequiresApi(Build.VERSION_CODES.O)
     fun LoadAllVidoes(){
         Log.d("LoadAllVideos","Loading")
         _states.value.isLoading.value=false
         Log.d("isLoading in LoadAllVideos", _states.value.isLoading.value.toString())
          viewModelScope.launch {
              repo.getAllVideos(application =application ).collectLatest {
                _states.value.allVideos=it
                  Log.d("VideosinLoadAllVideoss",  _states.value.allVideos.toString())
                  Log.d("VideosinLoadAllVideos", it.toString())
                  Log.d("repo calling","repo called")
              }

          }
         Log.d("isLoading2 in LoadAllVideos", _states.value.isLoading.value.toString())
         //_states.value.isLoading.value=true
     }
    /*
    Executed during object creation: The init block is called immediately after the primary constructor.
    Multiple init blocks: A class can have multiple init blocks, and they are executed in the order they appear in the class.
    Access to constructor parameters: The init block can directly use the parameters of the primary constructor.

     */
    init {
        Log.d("function call", "Loadvideos")
        LoadAllVidoes()
    }

    private var _FolderStates=MutableStateFlow(VideoFolderStates())
    var FolderStates=_FolderStates.asStateFlow()
    fun LoadFolder(){
        _FolderStates.value.isLoading.value=false
        Log.d("isLoading in viewmodel:", _FolderStates.value.isLoading.value.toString())
        viewModelScope.launch {
            repo.videoByFolder(application).collectLatest {
                _FolderStates.value.folders.value=it
                Log.d("Folders in viewmodel:", it.toString())
            }
            Log.d("Folders in viewmodel:", _FolderStates.value.folders.value.toString())
        }
    }
    init {
        Log.d("LoadFolder:","called")
        LoadFolder()
    }

}