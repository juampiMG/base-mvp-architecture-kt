package com.jp.app.ui

import com.jp.app.BuildConfig
import com.jp.app.SampleApplication
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runner.RunWith
import org.junit.runners.model.Statement
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [21], application = SampleApplication::class, packageName = "com.jp.app")
abstract class BaseTest {

    @get:Rule
    public var mInjectMocksRule: TestRule = TestRule { base, description ->
        MockitoAnnotations.initMocks(this@BaseTest)
        base
    }

    @get:Rule
    var mImmediateSchedulersRule: TestRule = ImmediateSchedulersRule()


    private inner class ImmediateSchedulersRule : TestRule {
        override fun apply(base: Statement, description: Description): Statement {
            return object : Statement() {
                @Throws(Throwable::class)
                override fun evaluate() {
                    RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
                    RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
                    RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }

                    RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }

                    try {
                        base.evaluate()
                    } finally {
                        RxJavaPlugins.reset()
                        RxAndroidPlugins.reset()
                    }
                }
            }
        }
    }

    @Test
    abstract fun controlViews()

}
