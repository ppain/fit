package com.paint.fitapp.di

import android.content.Context
import android.net.ConnectivityManager
import com.paint.fitapp.App
import com.paint.fitapp.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkConnectionInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected) {
            throw NoConnectivityException(Constants.ERROR_NO_INTERNET_CONNECTION)
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    inner class NoConnectivityException : IOException {

        constructor(message: String) : super(message) {}

        constructor(throwable: Throwable) : super(throwable) {}

        constructor(message: String, throwable: Throwable) : super(message, throwable) {}

        fun message(): String {
            return Constants.ERROR_NO_INTERNET_CONNECTION
        }
    }

    companion object {
        val isConnected: Boolean
            get() {
                val connectivityManager = App.ctx
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val netInfo = connectivityManager.activeNetworkInfo
                return netInfo != null && netInfo.isConnected
            }
    }

}