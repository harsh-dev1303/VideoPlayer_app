package com.example.videoplayer.UI_layer.Screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VideoFile
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.example.videoplayer.UI_layer.SharedViewModel
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenUI(Sharedviewmodel: SharedViewModel, Navcontroller: NavHostController) {
    Column {
        val scope= rememberCoroutineScope()
      val Tab=listOf<EachTab>(
          EachTab(name = "Videos", image = Icons.Default.Videocam),
          EachTab(name = "File", image = Icons.Default.VideoFile)
      )
        val pagerstate= rememberPagerState(pageCount = { Tab.size })
        TabRow(selectedTabIndex = pagerstate.currentPage, modifier = Modifier.fillMaxWidth()) {
             Tab.forEachIndexed{index, eachTab ->
                 Tab(
                     selected = pagerstate.currentPage==index,
                     onClick = {
                         scope.launch {
                            pagerstate.animateScrollToPage(index)
                         }
                     },
                     text ={ Text(text = eachTab.name) },
                     icon = { Icon(eachTab.image, contentDescription = null) }
                 )
             }
        }
       HorizontalPager(state = pagerstate){
           when(it){
               0-> VideoScreenUi(Navcontroller)
               1-> FileScreenUi(Sharedviewmodel,Navcontroller)
           }
        }
    }

}
data class EachTab(
    val name:String,
    val image:ImageVector
)