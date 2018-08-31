package com.jp.app.ui.sample

import android.app.Activity
import com.jp.app.common.BaseActivityModule
import com.jp.app.injector.scope.PerActivity
import com.jp.app.injector.scope.PerFragment
import com.jp.app.ui.sample.view.SampleFragment
import com.jp.app.ui.sample.view.SampleFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = arrayOf(BaseActivityModule::class))
abstract class SampleActivityModule {
    @Binds
    @PerActivity
    internal abstract fun activity(activity: SampleActivity): Activity

    @Binds
    @PerActivity
    internal abstract fun fragmentCallback(activity: SampleActivity): SampleFragment.FragmentCallback

    @PerFragment
    @ContributesAndroidInjector(modules = arrayOf(SampleFragmentModule::class))
    internal abstract fun fragmentInjector(): SampleFragment
}
