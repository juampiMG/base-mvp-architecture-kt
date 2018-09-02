package com.jp.data.network.retrofit.service

import com.jp.data.entity.sample.ResultSampleEntity
import io.reactivex.Single
import retrofit2.http.GET

interface RestServices {
    @GET("games")
    fun getSamples(): Single<ResultSampleEntity>
}