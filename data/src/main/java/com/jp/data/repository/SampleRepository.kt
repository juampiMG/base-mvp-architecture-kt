package com.jp.data.repository

import com.jp.data.entity.mapper.SampleEntityMapper
import com.jp.data.network.retrofit.service.RestServices
import com.jp.domain.model.SampleDomain
import com.jp.domain.repository.ISampleRepository
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.functions.Function
import javax.inject.Inject

class SampleRepository
@Inject
constructor(restServices: RestServices) : ISampleRepository {
    @Inject
    internal lateinit  var mSampleEntityMapper: SampleEntityMapper


    private var mRestServices: RestServices = restServices

    override fun getSamples(): Single<List<SampleDomain>> {
        return mRestServices.getSamples().flatMap { resultEntities -> Single.just(mSampleEntityMapper!!.transform(resultEntities.data!!)) }
    }
}