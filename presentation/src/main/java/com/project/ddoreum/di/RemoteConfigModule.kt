package com.project.ddoreum.di

import com.project.ddoreum.data.datasource.challenge.RemoteConfigHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteConfigModule {

    @Singleton
    @Provides
    fun provideRemoteConfigHelper() = RemoteConfigHelper()
}