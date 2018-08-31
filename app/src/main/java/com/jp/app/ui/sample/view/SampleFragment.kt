package com.jp.app.ui.sample.view

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jp.app.R
import com.jp.app.common.view.BaseFragment
import com.jp.app.common.view.IBaseFragmentCallback
import com.jp.app.model.SampleView
import com.jp.app.ui.sample.adapter.SampleAdapter
import com.jp.app.ui.sample.presenter.ISamplePresenter
import com.jp.app.ui.sample.presenter.SamplePresenter
import kotlinx.android.synthetic.main.sample_fragment.*
import java.util.*

class SampleFragment : BaseFragment<ISamplePresenter, SampleFragment.FragmentCallback>(), ISampleView {

    private val mLayoutId = R.layout.sample_fragment

    private var mAdapter: SampleAdapter? = null

    companion object {

        fun newInstance(bundle: Bundle?) = SampleFragment().apply {
            arguments = bundle ?: Bundle()
        }
    }

    interface FragmentCallback : IBaseFragmentCallback {
        fun loadSampleInfo(Sample: SampleView)
    }

    override fun getLayoutId(): Int {
        return mLayoutId
    }


    override fun onViewLoaded(savedInstanceState: Bundle?, view: View?) {
        super.onViewLoaded(savedInstanceState, view)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        mAdapter = SampleAdapter(ArrayList(), getPresenter() as SamplePresenter)
        val manager = GridLayoutManager(activity, 3)

        recycler_view.apply {
            layoutManager = manager
            setHasFixedSize(false)
            adapter = mAdapter
        }

    }

    override fun drawSample(list: List<SampleView>){
        mAdapter!!.addSamples(list)
    }

    override fun loadSampleInfo(Sample: SampleView) {
        if (mCallback != null) {
            mCallback!!.loadSampleInfo(Sample)
        }
    }

    fun getAdapter(): SampleAdapter? {
        return mAdapter
    }

}
