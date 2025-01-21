package com.example.videoplayer.MyDI

import com.example.videoplayer.Data_layer.Repo.myRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DI_module {

    @Provides
    @Singleton
    fun provideRepo():myRepo{
        return myRepo()
    }
}