package com.project.ddoreum.domain

data class ApiResult<out T>(val status: Status, val responseData: T?, val error: String?) {
    enum class Status {
        SUCCESS, ERROR
    }

    companion object {
        fun <T> success(responseData: T): ApiResult<T> {
            return ApiResult(Status.SUCCESS, responseData, null)
        }

        fun <T> error(error: String?): ApiResult<T> {
            return ApiResult(Status.ERROR, null, error)
        }
    }
}