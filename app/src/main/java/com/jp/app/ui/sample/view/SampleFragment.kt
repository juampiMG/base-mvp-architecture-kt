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
import java.util.ArrayList

class SampleFragment : BaseFragment<ISamplePresenter, SampleFragment.FragmentCallback>(), ISampleView {

    val LAYOUT_ID = R.layout.sample_fragment

    private var mGridLayoutManager: LinearLayoutManager? = null
    private var mAdapter: SampleAdapter? = null

    fun newInstance(bundle: Bundle?): SampleFragment {
        var bundle = bundle
        val fragment = SampleFragment()
        bundle = bundle ?: Bundle()
        fragment.arguments = bundle
        return fragment
    }

    interface FragmentCallback : IBaseFragmentCallback {
        fun loadSampleInfo(Sample: SampleView)
    }

    override fun getLayoutId(): Int {
        return LAYOUT_ID
    }


    override fun onViewLoaded(savedInstanceState: Bundle, view: View?) {
        super.onViewLoaded(savedInstanceState, view)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        mGridLayoutManager = GridLayoutManager(activity, 3)
        mRecyclerView.setLayoutManager(mGridLayoutManager)
        mAdapter = SampleAdapter(ArrayList<E>(), getPresenter() as SamplePresenter)
        mRecyclerView.setAdapter(mAdapter)
    }

    override fun drawSample(list: List<SampleView>){
        mAdapter.addSamples(Sample)
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
