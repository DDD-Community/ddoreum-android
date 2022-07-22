package com.project.ddoreum.di

import android.content.Context
import com.project.ddoreum.data.datasource.challenge.ChallengeDataSource
import com.project.ddoreum.data.datasource.challenge.ChallengeDataSourceImpl
import com.project.ddoreum.data.datasource.challenge.RemoteConfigHelper
import com.project.ddoreum.data.datasource.local.LocalDataSource
import com.project.ddoreum.data.datasource.local.LocalDataSourceImpl
import com.project.ddoreum.data.datasource.mountain.MountainDataSource
import com.project.ddoreum.data.datasource.mountain.MountainDataSourceImpl
import com.project.ddoreum.data.service.MountainService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideMountainDataSource(service: MountainService): MountainDataSource {
        return MountainDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(@ApplicationContext appContext: Context): LocalDataSource {
        return LocalDataSourceImpl(appContext)
    }

    @Provides
    @Singleton
    fun provideChallengeDataSource(remoteConfigHelper: RemoteConfigHelper): ChallengeDataSource {
        return ChallengeDataSourceImpl(remoteConfigHelper)
    }
}