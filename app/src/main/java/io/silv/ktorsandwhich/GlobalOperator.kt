package io.silv.ktorsandwhich

import android.app.Application
import android.util.Log
import android.widget.Toast
import io.silv.ktorsandwich.operators.ApiResponseSuspendOperator
import io.silv.ktorsandwich.ApiResponse
import io.silv.ktorsandwich.StatusCode
import io.silv.ktorsandwich.map
import io.silv.ktorsandwich.message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GlobalOperator<T> constructor(
    private val application: Application
) : ApiResponseSuspendOperator<T>() {

    private val tag = "GLOBAL_RESPONSE_OPERATOR"

    override suspend fun onSuccess(apiResponse: ApiResponse.Success<T>) {
       withContext(Dispatchers.Main) {
           toast("Global Response Success")
       }
    }

    // handles error cases when the API request gets an error response.
    // e.g., internal server error.
    override suspend fun onError(apiResponse: ApiResponse.Failure.Error<T>) {
        withContext(Dispatchers.Main) {
            apiResponse.run {
                Log.d(tag, message())

                // handling error based on status code.
                when (statusCode) {
                    StatusCode.InternalServerError -> toast("InternalServerError")
                    StatusCode.BadGateway -> toast("BadGateway")
                    else -> toast("$statusCode(${statusCode.code}): ${message()}")
                }

                // map the ApiResponse.Failure.Error to a customized error model using the mapper.
                map {
                    Log.d(tag, "[Code: ${it.statusCode}]: ${it.message()}")
                }
            }
        }
    }

    // handles exceptional cases when the API request gets an exception response.
    // e.g., network connection error, timeout.
    override suspend fun onException(apiResponse: ApiResponse.Failure.Exception<T>) {
        withContext(Dispatchers.Main) {
            apiResponse.run {
                Log.d(tag, message())
                toast("Global Response Exception ${message()}")
            }
        }
    }

    private fun toast(message: String) {
        Toast.makeText(application, message, Toast.LENGTH_SHORT).show()
    }
}