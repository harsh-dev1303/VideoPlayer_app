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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.util.unpackInt1
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.videoplayer.UI_layer.Navigation.playerScreen

import com.example.videoplayer.UI_layer.ViewModel.MyViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@SuppressLint("PermissionLaunchedDuringComposition")
@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun VideoScreenUi(NavController:NavHostController,viewmodel:MyViewModel= hiltViewModel()) {
    val allVideos = viewmodel.AllVideostates.collectAsState()
    val permission = rememberPermissionState(
        android.Manifest.permission.READ_MEDIA_VIDEO
    )
    val permissionRequested = remember { mutableStateOf(false) }
    LaunchedEffect(permission.status.isGranted) {
        Log.d("Launched effect:","called")
        if (permission.status.isGranted) {
            viewmodel.LoadAllVidoes() // Call the function to load videos
        }
    }
    when {
        allVideos.value.isLoading.value -> {
            Log.d("isLoading", allVideos.value.isLoading.value.toString())
            Log.d("videos in Ui", allVideos.value.allVideos.toString())
            Box(
                modifier = Modifier.fillMaxSize().background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        allVideos.value.isLoading.value == false -> {


            Log.d("isLoading Value in ui", allVideos.value.isLoading.value.toString())
            if (permission.status.isGranted) {
                LazyColumn {
                    items(allVideos.value.allVideos.size) {
                        Log.d("videos in Ui", allVideos.value.allVideos.toString())
                        Card(modifier = Modifier.fillMaxWidth()
                            .wrapContentHeight().padding(horizontal = 10.dp),
                            onClick = {
                                NavController.navigate(playerScreen(path=allVideos.value.allVideos[it].path))
                            }
                        ) {
                            Column {
                                Text(text = allVideos.value.allVideos[it].title ?: "unknown")
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            } else if (!permission.status.isGranted) {           // Request permission if not granted
                if (permissionRequested.value == false) {
                    permissionRequested.value == true
                    LaunchedEffect(Unit) {
                        permission.launchPermissionRequest()

                        Log.d("isloading in permission", allVideos.value.isLoading.value.toString())

                    }
                    allVideos.value.isLoading.value = true
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize().background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "No Videos Found")
                }
            }


        }

    }
}
