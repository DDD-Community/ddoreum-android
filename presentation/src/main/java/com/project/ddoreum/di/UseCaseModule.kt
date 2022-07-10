package com.project.ddoreum.di

import com.project.ddoreum.domain.repository.ChallengeRepository
import com.project.ddoreum.domain.repository.MountainRepository
import com.project.ddoreum.domain.usecase.challenge.GetAllChallengeListUseCase
import com.project.ddoreum.domain.usecase.mountain.GetAllMountainInfoUseCase
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
    fun provideGetAllChallengeListUseCase(challengeRepository: ChallengeRepository) = GetAllChallengeListUseCase(challengeRepository)

}