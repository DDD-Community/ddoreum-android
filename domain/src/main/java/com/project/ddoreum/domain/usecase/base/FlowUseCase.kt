package com.project.ddoreum.domain.usecase.base

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<in P, R : Any?> {

    suspend operator fun invoke(param: P): Flow<R> {
        return execute(param)
    }

    protected abstract suspend fun execute(param: P): Flow<R>
}