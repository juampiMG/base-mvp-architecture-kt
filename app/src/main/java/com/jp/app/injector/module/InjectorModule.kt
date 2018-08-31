package com.jp.app.injector.module

import com.jp.app.injector.scope.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class InjectorModule {
    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(SampleActivityModule::class))
    internal abstract fun sampleActivity(): SampleActivity
}
