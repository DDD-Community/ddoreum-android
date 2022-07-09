package com.project.ddoreum.di

import com.project.ddoreum.domain.repository.MountainRepository
import com.project.ddoreum.domain.usecase.mountain.*
import com.project.ddoreum.domain.usecase.search.AddRecentSearchKeywordUseCase
import com.project.ddoreum.domain.usecase.search.DeleteRecentSearchKeywordUseCase
import com.project.ddoreum.domain.usecase.search.GetAllRecentSearchKeywordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetAllMountainInfoUseCase(mountainRepository: MountainRepository) = GetAllMountainInfoUseCase(mountainRepository)

    @Provides
    @ViewModelScoped
    fun provideGetMountainDetailInfoUseCase(mountainRepository: MountainRepository) = GetMountainDetailInfoUseCase(mountainRepository)

    @Provides
    @ViewModelScoped
    fun provideGetAllFavoriteMountainUseCase(mountainRepository: MountainRepository) = GetAllFavoriteMountainUseCase(mountainRepository)

    @Provides
    @ViewModelScoped
    fun provideAddFavoriteMountainUseCase(mountainRepository: MountainRepository) = AddFavoriteMountainUseCase(mountainRepository)

    @Provides
    @ViewModelScoped
    fun provideDeleteFavoriteMountainUseCase(mountainRepository: MountainRepository) = DeleteFavoriteMountainUseCase(mountainRepository)

    @Provides
    @ViewModelScoped
    fun provideGetMountainsInfoByKeywordUseCase(mountainRepository: MountainRepository) = GetMountainsInfoByKeywordUseCase(mountainRepository)

    @Provides
    @ViewModelScoped
    fun provideAddRecentSearchKeywordUseCase(mountainRepository: MountainRepository) = AddRecentSearchKeywordUseCase(mountainRepository)

    @Provides
    @ViewModelScoped
    fun provideDeleteRecentSearchKeywordUseCase(mountainRepository: MountainRepository) = DeleteRecentSearchKeywordUseCase(mountainRepository)

    @Provides
    @ViewModelScoped
    fun provideGetAllRecentSearchKeywordUseCase(mountainRepository: MountainRepository) = GetAllRecentSearchKeywordUseCase(mountainRepository)
}