package com.project.ddoreum.data

import com.project.ddoreum.data.ApiErrorCase.COMMON_ERROR
import com.project.ddoreum.data.ApiErrorCase.HTTP_EXCEPTION_ERROR
import com.project.ddoreum.data.ApiErrorCase.NETWORK_ERROR
import com.project.ddoreum.domain.ApiResult
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T> getResult(apiCall: suspend () -> Response<T>): ApiResult<T> {
    try {
        val response = apiCall()

        if (response.isSuccessful) {
            val body = response.body()
            body?.let {
                return ApiResult.success(body)
            }
        }

        return ApiResult.error(COMMON_ERROR)

    } catch (e: HttpException) {
        return ApiResult.error(HTTP_EXCEPTION_ERROR)

    } catch (e: Exception) {
        return ApiResult.error(NETWORK_ERROR)
    }
}

object ApiErrorCase {
    const val COMMON_ERROR = "server_error"
    const val HTTP_EXCEPTION_ERROR = "http_exception_error"
    const val NETWORK_ERROR = "network_error"
