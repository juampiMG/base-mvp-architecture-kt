package com.jp.app.ui.sample.view

import com.jp.app.common.view.IBaseView

interface ISampleView : IBaseView{
    fun drawSample(list: List<SampleView>)

    fun loadSampleInfo(sample: SampleView)
}
