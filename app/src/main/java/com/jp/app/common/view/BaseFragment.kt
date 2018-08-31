package com.jp.app.common.view

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jp.app.common.BaseActivity
import com.jp.app.common.presenter.IBasePresenter
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import javax.inject.Inject

abstract class BaseFragment<TPresenter : IBasePresenter, TCallback : IBaseFragmentCallback> : Fragment(), HasFragmentInjector, IBaseView {

    @Inject
    protected var mContext: Context? = null
    @Inject
    var mPresenter: TPresenter? = null
    @Inject
    var mCallback: TCallback? = null

    @Inject
    internal var mChildFragmentInjector: DispatchingAndroidInjector<Fragment>? = null

    private var mFragmentId: String? = null
    private var mLayoutId: Int = 0

    constructor() {
        val fragmentClass = (this as Any).javaClass
        mFragmentId = fragmentClass.name
    }

    // =============== HasFragmentInjector =========================================================

    override fun onAttach(activity: Activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // Perform injection here before M, L (API 22) and below because onAttach(Context)
            // is not yet available at L.
            AndroidInjection.inject(this)
        }
        super.onAttach(activity)
    }

    override fun onAttach(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Perform injection here for M (API 23) due to deprecation of onAttach(Activity).
            AndroidInjection.inject(this)
        }
        super.onAttach(context)
    }

    override fun fragmentInjector(): AndroidInjector<Fragment> {
        return mChildFragmentInjector!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLayoutId = getLayoutId()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle): View? {
        return inflater.inflate(mLayoutId, container, false)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle) {
        super.onViewStateRestored(savedInstanceState)
        /*
         * Bind the views here instead of in onViewCreated so that mView state changed listeners
         * are not invoked automatically without user interaction.
         *
         * If we bind before this method (e.g. onViewCreated), then any checked changed
         * listeners bound by ButterKnife will be invoked during fragment recreation (since
         * Android itself saves and restores the views' states. Take a look at this gist for a
         * concrete example: https://gist.github.com/vestrel00/982d585144423f728342787341fa001d
         *
         * The lifecycle order is as follows (same if added via xml or java or if retain
         * instance is true):
         *
         * onAttach
         * onCreateView
         * onViewCreated
         * onActivityCreated
         * onViewRestored
         * onStart
         * onResume
         *
         * Note that the onCreate (and other lifecycle events) are omitted on purpose. The
         * caveat to this approach is that views, listeners, and resources bound by
         * Butterknife will be null until onViewStatedRestored. Just be careful not to use any
         * objects bound using Butterknife before onViewRestored.
         *
         * Fragments that do not return a non-null View in onCreateView results in onViewCreated
         * and onViewRestored not being called. This means that Butterknife.bind will not get
         * called, which is completely fine because there is no View to bind. Furthermore, there is
         * no need to check if getView() returns null here because this lifecycle method only gets
         * called with a non-null View.
         */
        onViewLoaded(savedInstanceState, view)
        mPresenter!!.onViewReady()
    }

    open fun onViewLoaded(savedInstanceState: Bundle, view: View?) {}

    override fun onDestroy() {
        super.onDestroy()
        mPresenter!!.detachView()
    }

    override fun onPause() {
        super.onPause()
        mPresenter!!.pause()
    }

    override fun showLoading() {
            mCallback?.showLoading()
    }

    override fun hideLoading() {
        if (mCallback != null) {
            mCallback!!.hideLoading()
        }
    }

    override fun showError(title: String, message: String, actionOnError: BaseActivity.ActionOnError) {
            mCallback?.showError(title, message, actionOnError)
    }

    abstract fun getLayoutId(): Int


    fun getFragmentId(): String {
        return mFragmentId!!
    }

    fun getPresenter(): TPresenter {
        return mPresenter!!
    }

    fun setPresenter(presenter: TPresenter) {
        mPresenter = presenter
    }

}
