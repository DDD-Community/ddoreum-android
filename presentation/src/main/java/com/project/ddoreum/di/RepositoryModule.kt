package com.project.ddoreum.di

import com.project.ddoreum.data.datasource.mountain.MountainDataSource
import com.project.ddoreum.data.repository.MountainRepositoryImpl
import com.project.ddoreum.domain.repository.MountainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMountainRepository(
        mountainDataSource: MountainDataSource
    ): MountainRepository {
        return MountainRepositoryImpl(
            mountainDataSource = mountainDataSource
        )
    }

}