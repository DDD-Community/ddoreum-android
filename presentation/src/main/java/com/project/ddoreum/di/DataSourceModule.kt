package com.project.ddoreum.di

import com.project.ddoreum.data.datasource.mountain.MountainDataSource
import com.project.ddoreum.data.datasource.mountain.MountainDataSourceImpl
import com.project.ddoreum.data.service.MountainService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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

}