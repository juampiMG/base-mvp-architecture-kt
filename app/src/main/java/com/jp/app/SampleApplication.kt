package com.jp.app

import android.app.Activity
import android.os.Build
import android.support.multidex.MultiDexApplication
import android.support.v7.app.AppCompatDelegate
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class SampleApplication :  MultiDexApplication(), HasActivityInjector {

    @Inject
    internal lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        initCompatVector()
        initDaggerApplicationComponent()
    }

    // =============== HasActivityInjector =========================================================

    override fun activityInjector(): AndroidInjector<Activity>? {
        return activityInjector
    }

    // =============== Support methods =============================================================

    private fun initCompatVector() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }

    private fun initDaggerApplicationComponent() {
        DaggerApplicationComponent.builder().create(this).inject(this)
    }

}
