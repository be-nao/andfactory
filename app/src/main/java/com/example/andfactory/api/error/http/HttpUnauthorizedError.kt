package com.example.andfactory.api.error.http

import net.gahfy.mvvm_base.api.error.ApiError
import net.gahfy.mvvm_base.api.error.ApiErrorResponse
import retrofit2.HttpException

open class HttpUnauthorizedError(
    httpException: HttpException,
    response: ApiErrorResponse
) : ApiError(httpException, response) {

    companion object {
        const val HTTP_CODE = 401
    }
}
