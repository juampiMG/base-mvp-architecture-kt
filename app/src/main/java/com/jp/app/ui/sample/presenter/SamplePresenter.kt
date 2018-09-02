package com.jp.app.ui.sample.presenter

import com.jp.app.common.BaseActivity
import com.jp.app.common.BaseSingleObserver
import com.jp.app.common.presenter.BasePresenter
import com.jp.app.model.SampleView
import com.jp.app.model.mapper.SampleViewMapper
import com.jp.app.ui.sample.adapter.SampleAdapter
import com.jp.app.ui.sample.view.ISampleView
import com.jp.domain.interactor.IGetSampleUseCase
import com.jp.domain.model.SampleDomain
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject

class SamplePresenter @Inject
internal constructor(sampleView: ISampleView) : BasePresenter<ISampleView>(sampleView), ISamplePresenter, SampleAdapter.SampleAdapterCallBack {

    @Inject
    lateinit var mGetSampleUseCase : IGetSampleUseCase
    @Inject
    lateinit var mSampleViewMapper: SampleViewMapper


    private var mSampleDomain: List<SampleDomain>? = null

    private var mSampleView : List<SampleView> = ArrayList()

    override fun onViewReady() {
        loadSample()
    }

    private fun loadSample() {
        mView!!.showLoading()
        mGetSampleUseCase.execute().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseSingleObserver<List<SampleDomain>>(getContext()) {

                    override fun onSubscribe(d: Disposable) {
                        addDisposable(d)
                    }

                    override fun onSuccess(sample: List<SampleDomain>) {
                        if (mView != null) mView!!.hideLoading()
                            mSampleDomain = sample
                            mSampleView = mSampleViewMapper.transform(sample)
                            drawSampleTransformed(mSampleView)
                    }

                    override fun onError(code: Int, title: String, description: String) {
                        if (mView != null) mView!!.hideLoading()
                        showError(title, description, BaseActivity.ActionOnError.CLOSE)
                    }
                })
    }

    private fun drawSampleTransformed(list: List<SampleView>) {
        if (mView != null) {
            mView!!.drawSample(list)
        }
    }


    override fun sampleClicked(position: Int) {
        loadSampleInfo(mSampleView[position])
    }

    private fun loadSampleInfo(sample: SampleView) {
        if (mView != null) {
            mView!!.loadSampleInfo(sample)
        }
    }
}
