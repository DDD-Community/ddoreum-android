package com.project.ddoreum.domain.usecase.base

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

abstract class PagingUseCase<in P, R : Any> {

    operator fun invoke(param: P): Flow<PagingData<R>> {
        return execute(param)
    }

    protected abstract fun execute(param: P): Flow<PagingData<R>>
}