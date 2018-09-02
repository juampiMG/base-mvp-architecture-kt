package com.jp.data.entity.mapper

import com.jp.data.entity.sample.SampleEntity
import com.jp.domain.model.SampleDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SampleEntityMapper
@Inject
constructor(){

    fun transform(source: SampleEntity): SampleDomain {
        val sampleDomain = SampleDomain()
        try {
            sampleDomain.id = source.id
            if (source.assets!!.coverLarge != null) sampleDomain.urlLogo = source.assets!!.coverLarge!!.uri
            if (source.names != null) sampleDomain.title = source.names!!.international
        } catch (e: Exception) {
            e.stackTrace
        }

        return sampleDomain
    }

    fun inverseTransform(source: SampleDomain): SampleEntity {
        val sampleEntity = SampleEntity()
        try {
        } catch (e: Exception) {
            e.stackTrace
        }

        return sampleEntity
    }

}