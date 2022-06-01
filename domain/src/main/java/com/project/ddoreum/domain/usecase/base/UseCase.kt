package com.project.ddoreum.domain.usecase.base

abstract class UseCase<in P, R : Any?> {

    suspend operator fun invoke(param: P): R {
        return execute(param)
    }

    protected abstract suspend fun execute(param: P): R
}