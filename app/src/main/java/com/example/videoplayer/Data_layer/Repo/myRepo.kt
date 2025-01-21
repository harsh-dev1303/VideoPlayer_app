package com.example.videoplayer.Data_layer.Repo

import android.app.Application
import android.content.ContentResolver
import android.content.ContentUris
import android.os.Build
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.videoplayer.Data_layer.videoFile
import com.example.videoplayer.Domain_Layer.RepoMethodsDeclaration
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.io.File

class myRepo: RepoMethodsDeclaration {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getAllVideos(application: Application): Flow<List<videoFile>> {
         var AllVideos=ArrayList<videoFile>()
        val projection= arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DATA,      //for path
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.DISPLAY_NAME,   //for fileName
            MediaStore.Video.Media.DATE_ADDED,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.DURATION,
        )

        //EXTERNAL_CONTENT_URI EXTERNAL_CONTENT_URI is a Uri that points to the collection of media files stored on the device's external storage.:
        val uri=MediaStore.Video.Media.EXTERNAL_CONTENT_URI              //yeh batata hai ki cursor ko kaha se scanning start karni hai
        val cursor=application.contentResolver.query(uri,projection,null,null)    //yehbpuri storage ko scan karneke liye
        if (cursor!=null){
            Log.d("Cursor State", "Cursor has ${cursor.count} items")
            while (cursor.moveToNext()){
                val Id=cursor.getString(0)
                val Path=cursor.getString(1)
                val Title=cursor.getString(2)
                val FileName=cursor.getString(3)
                val DateAdded=cursor.getString(4)
                val size=cursor.getString(5)
                val Duration=cursor.getString(6)
                val Thumbnail=ContentUris.appendId(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI.buildUpon(),
                    Id.toLong()
                ).build()
                Log.d("Video File", "Id: $Id, Path: $Path, Title: $Title, FileName: $FileName")

                val videofile=videoFile(
                    id=Id,
                    path=Path,
                    title = Title,
                    fileName = FileName,
                    dateAdded = DateAdded,
                    size = size,
                    duration = Duration,
                    thumbnail = Thumbnail.toString()
                )
                AllVideos.add(videofile)
            }
        }
        if (cursor != null) {
            cursor.close()
        }
        Log.d("videos in repo:", AllVideos.toString())
        return flow {
             emit(AllVideos)
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun videoByFolder(application: Application): Flow<Map<String, List<videoFile>>> {
        val Videos=getAllVideos(application).first()    //first()-> it is used to get values one by one emmited by flow well here on list will be collected but then also it is the way to collect values emmitted by flow
        val VideoByFolder=Videos.groupBy {    //groupBy-> is collection (like lists) method to group it in map or get a value for key in map
            File(it.path).parentFile.name?:"Unknown"    //here it represents Each videofile from list and by using its path File object is created and folder name is fetched.
        }
        VideoByFolder.forEach { (folder, videos) ->
            Log.d("Folder", "Name: $folder, Videos: ${videos.size}")
        }

        Log.d("folders in repo: ",VideoByFolder.toString())
        Log.d("videos of folders in repo: ",Videos.toString())
        return flow { emit(VideoByFolder) }


    }
}