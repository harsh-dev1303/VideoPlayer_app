package com.example.videoplayer.UI_layer.Screens

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.videoplayer.Data_layer.videoFile
import com.example.videoplayer.UI_layer.Navigation.playerScreen
import com.example.videoplayer.UI_layer.SharedViewModel
import com.example.videoplayer.UI_layer.ViewModel.MyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("StateFlowValueCalledInComposition", "UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun VideosByFile(
    sharedviewmodel: SharedViewModel,
    Navcontroller: NavHostController,

) {
    val videoList = sharedviewmodel.videoList.collectAsState()
    Log.d("sharedviewmodel.videoList in file:", sharedviewmodel.videoList.value.toString())
    Log.d("videoList in videosbyfile:", videoList.value.toString())
    Log.d("Destination", "Video list observed: ${videoList.value.size}")


    if (videoList.value == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Log.d("if here:", "running")
                Text("Loading")
            }
    } else if (videoList.value != null) {
        Log.d("elsevideoList in videosbyfile:", videoList.value.toString())
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("Videos")
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                Navcontroller.popBackStack()
                            }
                        ) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription =null)
                        }
                    },
                    windowInsets = WindowInsets(top = 0.dp)
                )

            }
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Log.d("else if:", "running")
                LazyColumn(modifier = Modifier.fillMaxSize().background(Color.White)) {
                    items(videoList.value.size) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(horizontal = 10.dp)
                                .background(Color.White),
                            onClick = {
                                Navcontroller.navigate(playerScreen(videoList.value[it].path))
                            }
                        ) {
                            Text(text = videoList.value[it].title.toString())

                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                }

            }
        }
    }

}
