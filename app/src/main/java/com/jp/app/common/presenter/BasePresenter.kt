package com.jp.app.common.presenter

import android.content.Context
import com.jp.app.common.BaseActivity
import com.jp.app.common.view.IBaseView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BasePresenter  <TView : IBaseView>(view: TView) : IBasePresenter {
    @Inject
    internal var mContext: Context? = null

    protected var mView: TView? = view

    private var mCompositeDisposable = CompositeDisposable()


    override fun detachView() {
        mView = null
    }

    override fun pause() {
            removeAllDisposables()
    }

    protected fun showError(title: String, description: String, actionOnError: BaseActivity.ActionOnError) {
        if (mView != null) {
            mView!!.showError(title, description, actionOnError)
        }
    }

    fun getCompositeDisposable(): CompositeDisposable {
        if (mCompositeDisposable.isDisposed)
            mCompositeDisposable = CompositeDisposable()
        return mCompositeDisposable
    }

    fun addDisposable(disposable: Disposable?) {
        if (disposable != null ) {
            mCompositeDisposable.add(disposable)
        }
    }

    fun removeDisposable(disposable: Disposable?) {
        if (disposable != null) {
            if (!disposable.isDisposed) {
                disposable.dispose()
            }
            mCompositeDisposable.remove(disposable)
        }
    }

    fun getContext(): Context? {
        return mContext
    }

    fun removeAllDisposables() {
        mCompositeDisposable.clear()
    }

    fun hasDisposables(): Boolean {
        return  mCompositeDisposable.size() > 0
    }

    fun setView(view: TView) {
        mView = view
    }

    abstract override fun onViewReady()
}
