package com.example.andfactory.api.error.http


import net.gahfy.mvvm_base.api.error.ApiError
import net.gahfy.mvvm_base.api.error.ApiErrorResponse
import retrofit2.HttpException

class HttpConflictError(
    httpException: HttpException,
    response: ApiErrorResponse
) : ApiError(httpException, response) {

    companion object {
        const val HTTP_CODE = 409
    }
}
