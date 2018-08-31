package com.jp.app.common.presenter

interface IBasePresenter {
    fun detachView()

    fun pause()

    fun onViewReady()
}
