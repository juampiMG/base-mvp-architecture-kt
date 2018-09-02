package com.jp.app.common

import android.content.Context
import com.jp.app.R
import com.jp.data.exception.AppException
import com.omvp.data.network.gateway.retrofit.exception.RetrofitException
import io.reactivex.SingleObserver
import io.reactivex.annotations.NonNull
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException

abstract class BaseSingleObserver<T>(context: Context) : SingleObserver<T> {
    private var mContext: Context? = context

    override fun onError(@NonNull e: Throwable) {
        handleError(e)
    }

    protected abstract fun onError(code: Int, title: String, description: String)

    private fun handleError(throwable: Throwable) {
        val code = 0
        val title = mContext!!.getString(R.string.oh_hell)
        val message = mContext!!.getString(R.string.default_error)

        if (throwable is RetrofitException) {
            handleRetrofitException(throwable as RetrofitException)
        } else if (throwable is AppException) {
            handleAppException(throwable)
        } else {
            onError(code, title, message)
        }
    }

    private fun handleRetrofitException(exception: RetrofitException) {
        val url = exception.url
        val response = exception.response
        val cause = exception.cause
        var code = if (response != null) response!!.code() else 0
        val title = mContext!!.getString(R.string.oh_hell)
        var message = mContext!!.getString(R.string.default_error)
        if (cause is ConnectException) {
            message = mContext!!.getString(R.string.default_error)
        } else if (cause is SocketTimeoutException) {
            message = mContext!!.getString(R.string.default_error)
        } else {
            try {
                val jsonError = exception.getErrorBodyAs(JSONObject::class.java)
                if (jsonError != null) {
                    try {
                        if (jsonError!!.has("code") && jsonError!!.has("description")) {
                            code = jsonError!!.getInt("code")
                            message = jsonError!!.getString("description")
                        }
                    } catch (e: JSONException) {
                    }

                }
            } catch (e: IOException) {
            }

        }
        onError(code, title, message)
    }

    private fun handleAppException(exception: AppException) {
        val code = exception.mCode
        val title = mContext!!.getString(R.string.oh_hell)
        val message = exception.message
        onError(code!!, title, message!!)
    }
}