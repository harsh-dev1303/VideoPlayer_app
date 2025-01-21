package com.example.videoplayer.UI_layer.Screens

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.videoplayer.UI_layer.Navigation.videosByFile
import com.example.videoplayer.UI_layer.SharedViewModel

import com.example.videoplayer.UI_layer.ViewModel.MyViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FileScreenUi(Sharedviewmodel:SharedViewModel,navcontroller:NavHostController,viewmodel: MyViewModel = hiltViewModel()){
    val allFolders = viewmodel.FolderStates.collectAsState()
   /* val permission = rememberPermissionState(
        android.Manifest.permission.READ_MEDIA_VIDEO
    )
    val permissionRequested = remember { mutableStateOf(false) }
    LaunchedEffect(permission.status.isGranted) {
        Log.d("Launched effect:","called")
        if (permission.status.isGranted) {
            viewmodel.LoadAllVidoes() // Call the function to load videos
        }
    }*/
    LaunchedEffect(Unit) {
        viewmodel.LoadFolder()
    }
    when {
        allFolders.value.isLoading.value -> {
            Log.d("isLoading", allFolders.value.isLoading.value.toString())
            Log.d("videos in Ui", allFolders.value.folders.toString())
            Box(
                modifier = Modifier.fillMaxSize().background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        allFolders.value.isLoading.value == false -> {

            Log.d("allFolders.value.isLoading.value == false:","called")
           // Log.d("isLoading Value in ui", allVideos.value.isLoading.value.toString())
           // if (permission.status.isGranted) {
                LazyColumn(modifier=Modifier.background(Color.White).fillMaxSize()) {
                    items(allFolders.value.folders.value.entries.toList()) {folderEntry->     //Output: [Folder1=[Video1, Video2], Folder2=[Video3], Folder3=[Video4, Video5, Video6]]
                        val folderName = folderEntry.key
                        val videos = folderEntry.value
                       // Log.d("viewmodel.Videos.value:",viewmodel.Videos.value.toString())
                      //  Log.d("Source", "Video list set: ${viewmodel.Videos.value.size}")
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(horizontal = 10.dp)
                                .background(Color.White),
                            onClick = {
                                Sharedviewmodel.CollectVideoList(videos)
                                navcontroller.navigate(videosByFile)
                            }
                        ) {
                            Column(
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Text(
                                    text = folderName,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Videos: ${videos.size}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    }
                }
                else-> {
                Box(
                    modifier = Modifier.fillMaxSize().background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "No Videos Found")
                }
            }


        }

    }
