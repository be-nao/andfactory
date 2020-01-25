package net.gahfy.mvvm_base.api.error

import retrofit2.HttpException

class InternalServerError(
    httpException: HttpException,
    response: ApiErrorResponse
) : ApiError(httpException, response) {

    companion object {
        const val HTTP_CODE = 500

        fun create(httpException: HttpException, response: ApiErrorResponse): ApiError {
            return InternalServerError(httpException, response)
        }
    }
}
