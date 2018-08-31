package com.jp.app.ui.sample

import android.os.Bundle
import com.jp.app.R
import com.jp.app.common.BaseActivity
import com.jp.app.model.SampleView
import com.jp.app.ui.sample.view.SampleFragment
import com.jp.app.utils.NavigationUtils

class SampleActivity : BaseActivity(), SampleFragment.FragmentCallback{

    private val mLayoutId = R.layout.sample_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       if (savedInstanceState == null) {
            currentFragment = SampleFragment.newInstance(Bundle())
            NavigationUtils.navigateToFragment(this, currentFragment!!, R.id.content, false)
        } else {
            currentFragment = fragmentManager.findFragmentById(R.id.content)
        }
    }

    override fun getLayoutReference(): Int {
        return mLayoutId
    }

    override fun loadSampleInfo(sample: SampleView) {
        NavigationUtils.navigationToSampleInfoActivity(this, sample)
        showMessage(getString(R.string.information), String.format(getString(R.string.message_on_click), sample.title))
    }
}
