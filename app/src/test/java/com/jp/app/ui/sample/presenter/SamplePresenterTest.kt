package com.jp.app.ui.sample.presenter

import com.jp.app.model.SampleView
import com.jp.app.ui.BaseTest
import com.jp.app.ui.sample.SampleActivity
import com.jp.app.ui.sample.view.ISampleView
import com.jp.app.ui.sample.view.SampleFragment
import com.nhaarman.mockito_kotlin.argumentCaptor
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.robolectric.Robolectric
import org.robolectric.android.controller.ActivityController

class SamplePresenterTest : BaseTest() {

    @Mock
    internal var mView: ISampleView? = null
    var mPresenter: SamplePresenter? = null
    var mActivityController: ActivityController<SampleActivity>? = null
    var mActivity: SampleActivity? = null
    var mFragment: SampleFragment? = null

    @Before
    fun setup() {
        mActivityController = Robolectric.buildActivity(SampleActivity::class.java)
        mActivityController!!.create().start().resume()
        mActivity = mActivityController!!.get()
        mFragment = mActivity!!.currentFragment as SampleFragment?
        mPresenter = mFragment!!.mPresenter as SamplePresenter
        mPresenter!!.setView(mView!!)

    }


    override fun controlViews() {
        assertNotNull(mActivity)
        assertNotNull(mFragment)
        assertNotNull(mPresenter)
        assertNotNull(mView)
    }

    @Test
    fun checkLoadSample() {
        mPresenter!!.onViewReady()

        val captor = argumentCaptor<List<SampleView>>()
        verify<ISampleView>(mView).drawSample(captor.capture())
        val capturedArgument = captor.firstValue

        assertEquals(3, capturedArgument.size)
    }
}