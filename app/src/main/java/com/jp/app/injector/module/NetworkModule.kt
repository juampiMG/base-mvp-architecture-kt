package com.jp.app.injector.module

import com.jp.app.BuildConfig
import com.jp.data.Constants
import com.jp.data.network.interceptor.RequestInterceptor
import com.jp.data.network.retrofit.callAdapter.RxErrorHandlingCallAdapterFactory
import com.jp.data.network.retrofit.service.RestServices
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {


    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {

        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    @Singleton
    @Provides
    fun provideRequestInterceptor(): RequestInterceptor {
        return RequestInterceptor()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, requestInterceptor: RequestInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(requestInterceptor)
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRestServices(okHttpClient: OkHttpClient): RestServices {
        val restAdapter = Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .build()
        return restAdapter.create(RestServices::class.java)
    }

}
