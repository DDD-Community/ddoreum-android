package com.project.ddoreum.domain.entity

import com.project.ddoreum.domain.ApiResult

interface EntityMapper<FROM, TO> {
    fun map(model: FROM): TO
}

suspend fun <T, R> returnMapper(mapper: EntityMapper<T, R>, apiResult: suspend () -> ApiResult<T>): ApiResult<R> {
    val apiResponse = apiResult.invoke()
    return if (apiResponse.responseData != null) {
        ApiResult.success(mapper.map(apiResponse.responseData))
    } else {
        ApiResult.error(apiResponse.error)
    }
}