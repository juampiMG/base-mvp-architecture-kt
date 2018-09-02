package com.jp.app.ui.sample.view

import android.app.Dialog
import com.jp.app.R
import com.jp.app.ui.BaseTest
import com.jp.app.ui.sample.SampleActivity
import com.jp.app.ui.sample.adapter.SampleAdapter
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.robolectric.Robolectric
import org.robolectric.shadows.ShadowDialog
import org.robolectric.util.FragmentTestUtil.startFragment

class SampleViewTest : BaseTest() {
    private var mActivity: SampleActivity? = null

    private var mFragment: SampleFragment? = null

    @Before
    fun setup() {
        mActivity = Robolectric.setupActivity(SampleActivity::class.java)

        mFragment = mActivity!!.fragmentManager.findFragmentById(R.id.content) as SampleFragment

        startFragment(mFragment)
    }


    override fun controlViews() {
        assertNotNull(mActivity)
        assertNotNull(mFragment)
    }

    @Test
    fun checkRecyclerViewNotNull() {
        assertNotNull(mFragment!!.getRecyclerView())
    }

    @Test
    fun checkAdapter() {
        assertNotNull(mFragment!!.getAdapter())
    }

    @Test
    fun checkAdapterLoadAllData() {
        assertEquals(3, mFragment!!.getAdapter()!!.itemCount)
    }


    @Test
    fun checkFirstRowViewsData() {

        val holder = mFragment!!.getAdapter()!!.onCreateViewHolder(mFragment!!.getRecyclerView(), 0) as SampleAdapter.ItemViewHolder
        mFragment!!.getAdapter()!!.onBindViewHolder(holder, 0)

        assertNotNull(holder)
        assertEquals("SampleDomain1", holder.textView.text.toString())

    }

    @Test
    fun checkOnClickFirstRowData() {

        mFragment!!.getRecyclerView().getChildAt(0).performClick()
        val dialog = ShadowDialog.getLatestDialog()
        assertThat<Dialog>("The dialog should be displayed", dialog, `is`<Any>(notNullValue()))

        //        Intent expectedIntent = new Intent(mActivity, SampleInfoActivity.class);
        //        ShadowActivity shadowActivity = Shadows.shadowOf(mActivity);
        //        Intent actualIntent = shadowActivity.getNextStartedActivity();
        //        assertTrue(actualIntent.filterEquals(expectedIntent));
    }

}