package com.jp.app.common.view

import com.jp.app.common.BaseActivity

interface IBaseView{
    fun showLoading()

    fun hideLoading()

    fun showError(title: String, message: String, actionOnError: BaseActivity.ActionOnError)
}
