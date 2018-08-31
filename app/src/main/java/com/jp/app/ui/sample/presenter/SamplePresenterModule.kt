package com.jp.app.ui.sample.presenter

import com.jp.app.common.presenter.BasePresenterModule
import com.jp.app.injector.scope.PerFragment
import dagger.Binds
import dagger.Module

@Module(includes = arrayOf(BasePresenterModule::class))
abstract class SamplePresenterModule {

    @Binds
    @PerFragment
    internal abstract fun presenter(presenter: SamplePresenter): ISamplePresenter
}
