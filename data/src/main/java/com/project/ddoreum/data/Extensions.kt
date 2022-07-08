package com.project.ddoreum.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.ddoreum.data.ApiErrorCase.COMMON_ERROR
import com.project.ddoreum.data.ApiErrorCase.HTTP_EXCEPTION_ERROR
import com.project.ddoreum.data.ApiErrorCase.NETWORK_ERROR
import com.project.ddoreum.data.ApiErrorCase.SEARCH_KEYWORD_ERROR
import com.project.ddoreum.domain.ApiResult
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T> getResult(searchType: Boolean = false, apiCall: suspend () -> Response<T>): ApiResult<T> {
    try {
        val response = apiCall()

        if (searchType) {
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return ApiResult.success(body)
                }
            } else if(response.code() == 500) {
                return ApiResult.error(SEARCH_KEYWORD_ERROR)
            }

        } else {
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return ApiResult.success(body)
                }
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
    const val SEARCH_KEYWORD_ERROR = "search_keyword_error"
}

inline fun <reified T> Gson.fromJson(json:String): T = fromJson<T>(json, object: TypeToken<T>() {}.type)