package com.jp.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {

   private val mUserAgent = "User-Agent"
   private val mAccept = "Accept"
   private val mContentType = "Content-Type"

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val url = original.url().toString()

        // Customize the request
        val request = original.newBuilder()
                .header(mUserAgent, "Apache-HttpClient/4.3.1")
                .header(mAccept, "application/json")
                .header(mContentType, "application/json")
                .url(url)
                .method(original.method(), original.body())


        return chain.proceed(request.build())
    }
}