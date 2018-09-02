package com.jp.data

import com.jp.app.model.SampleView
import com.jp.domain.model.SampleDomain
import java.util.ArrayList

object ServerMock {
    fun getSampleView(): SampleView {
        val sampleView = SampleView()
        sampleView.id = "12345"
        sampleView.title = "Sample test"
        sampleView.urlLogo = "http//logo"
        return sampleView
    }

    fun getListDomain(): List<SampleDomain> {
        val g1 = SampleDomain()
        g1.id = "1234"
        g1.title = "SampleDomain1"
        g1.urlLogo = "http//logo1"

        val g2 = SampleDomain()
        g2.id = "1234"
        g2.title = "SampleDomain2"
        g2.urlLogo = "http//logo2"

        val g3 = SampleDomain()
        g3.id = "1234"
        g3.title = "SampleDomain3"
        g3.urlLogo = "http//logo3"

        val list : MutableList<SampleDomain> = ArrayList()
        list.add(g1)
        list.add(g2)
        list.add(g3)

        return list
    }
}