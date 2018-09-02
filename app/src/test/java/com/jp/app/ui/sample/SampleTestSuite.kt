package com.jp.app.ui.sample

import com.jp.app.ui.sample.presenter.SamplePresenterTest
import com.jp.app.ui.sample.view.SampleViewTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(SamplePresenterTest::class, SampleViewTest::class)
class SampleTestSuite
