package com.jp.app.ui.sample.view

import android.app.Fragment
import com.jp.app.common.view.BaseFragmentModule
import com.jp.app.injector.scope.PerFragment
import com.jp.app.ui.sample.presenter.SamplePresenterModule
import dagger.Binds
import dagger.Module

@Module(includes = [BaseFragmentModule::class, SamplePresenterModule::class])
abstract class SampleFragmentModule {
    @Binds
    @PerFragment
    internal abstract fun fragment(fragment: SampleFragment): Fragment

    @Binds
    @PerFragment
    internal abstract fun view(fragment: SampleFragment): ISampleView
}

