package com.jp.app.ui.sample.view

import com.jp.app.common.view.IBaseView
import com.jp.app.model.SampleView

interface ISampleView : IBaseView{
    fun drawSample(list: List<SampleView>)

    fun loadSampleInfo(sample: SampleView)
}
