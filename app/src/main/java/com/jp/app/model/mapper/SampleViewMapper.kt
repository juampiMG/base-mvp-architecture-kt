package com.jp.app.model.mapper

import com.jp.app.model.SampleView
import com.jp.data.entity.mapper.BaseModelDataMapper
import com.jp.domain.model.SampleDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SampleViewMapper
@Inject
constructor() : BaseModelDataMapper<SampleDomain, SampleView>() {
    override fun transform(source: SampleDomain): SampleView {
        val sampleView = SampleView()
        try {
            sampleView.id = source.id
            sampleView.title = source.title
            sampleView.urlLogo = source.urlLogo
        } catch (e: Exception) {
            e.stackTrace
        }

        return sampleView
    }


    override fun inverseTransform(source: SampleView): SampleDomain {
        val sampleDomain = SampleDomain()
        try {
            sampleDomain.id = source.id
            sampleDomain.title = source.title
            sampleDomain.urlLogo = source.urlLogo
        } catch (e: Exception) {
            e.stackTrace
        }

        return sampleDomain
    }
}