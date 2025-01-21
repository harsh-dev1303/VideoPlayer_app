package com.example.videoplayer.UI_layer.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.videoplayer.Data_layer.videoFile
import com.example.videoplayer.UI_layer.Screens.FileScreenUi
import com.example.videoplayer.UI_layer.Screens.HomeScreenUI
import com.example.videoplayer.UI_layer.Screens.PlayerScreen
import com.example.videoplayer.UI_layer.Screens.VideoScreenUi
import com.example.videoplayer.UI_layer.Screens.VideosByFile
import com.example.videoplayer.UI_layer.SharedViewModel
import kotlinx.serialization.Contextual

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationScreen(
    sharedviewmodel:SharedViewModel= hiltViewModel()
){
    val Navcontroller= rememberNavController()
    NavHost(navController = Navcontroller, startDestination = homeScreen){
        composable<homeScreen>{
            HomeScreenUI(sharedviewmodel,Navcontroller)
        }
        composable<videoscreen>{
            VideoScreenUi(Navcontroller)
        }
        composable<fileScreen>{
            FileScreenUi(sharedviewmodel,Navcontroller)
        }
        composable<playerScreen> {
           val url: String =it.toRoute<playerScreen>().path
            PlayerScreen(url)
        }
        composable<videosByFile> {

            VideosByFile(sharedviewmodel,Navcontroller)
        }
    }
}


