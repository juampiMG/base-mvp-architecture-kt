package com.jp.app.common

import android.app.AlertDialog
import android.app.Fragment
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.jp.app.R
import com.jp.app.common.view.IBaseFragmentCallback
import com.jp.app.ui.sample.SampleActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.loading_fragment.*
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasFragmentInjector, IBaseFragmentCallback {

    private val DEFAULT_NUM_COUNT_BACK = 1

    private var layoutId: Int = 0

    protected var currentFragment: Fragment? = null

    enum class actionOnError {
        CLOSE, NOTHING
    }

    @Inject
    internal var mFragmentInjector: DispatchingAndroidInjector<Fragment>? = null

    private var compositeDisposable: CompositeDisposable? = null


    private var exitToast: Toast? = null
    private var defaultCountBackToExit: Int = 0
    private var countBackToExit: Int = 0

    private val handler = Handler()
    private val restartCountBackToExit = Runnable { countBackToExit = defaultCountBackToExit }


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        layoutId = getLayoutReference()

        setContentView(layoutId)

        if (defaultCountBackToExit == 0) {
            defaultCountBackToExit = DEFAULT_NUM_COUNT_BACK
        }
        countBackToExit = defaultCountBackToExit
    }

    // =============== HasFragmentInjector =========================================================

    override fun fragmentInjector(): AndroidInjector<Fragment> {
        return mFragmentInjector!!
    }

    override fun onBackPressed() {
        manageBackPressed()

    }

    protected fun manageBackPressed() {
        if (fragmentManager.backStackEntryCount > 0) {
            super.onBackPressed()
        }
        if (this is SampleActivity) {
            if (exitToast == null) {
                exitToast = Toast.makeText(this, getString(R.string.count_back_exit_message), Toast.LENGTH_SHORT)
            }
            if (countBackToExit > 0) {
                countBackToExit--
                removeCallbacks()
                handler.postDelayed(restartCountBackToExit, 2000)
                exitToast!!.show()
                fragmentManager.popBackStack()
            } else {
                exitToast!!.cancel()
                super.onBackPressed()
            }

        } else {
            super.onBackPressed()
        }


    }

    private fun removeCallbacks() {
        handler?.removeCallbacks(restartCountBackToExit)
    }

    fun getCompositeDisposable(): CompositeDisposable {
        if (compositeDisposable == null || compositeDisposable!!.isDisposed)
            compositeDisposable = CompositeDisposable()
        return compositeDisposable!!
    }

    fun addDisposable(disposable: Disposable?) {
        if (disposable != null && compositeDisposable != null) {
            compositeDisposable!!.add(disposable)
        }
    }

    fun removeDisposable(disposable: Disposable?) {
        if (disposable != null) {
            if (!disposable.isDisposed) {
                disposable.dispose()
            }
            if (compositeDisposable != null) {
                compositeDisposable!!.remove(disposable)
            }
        }
    }

    fun removeAllDisposables() {
        if (compositeDisposable != null) {
            compositeDisposable?.clear()
        }
    }

    fun hasDisposables(): Boolean {
        return if (compositeDisposable != null) {
            compositeDisposable!!.size() > 0
        } else {
            false
        }
    }

    private fun showErrorDialog(title: String, message: String, action: actionOnError) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
                .setTitle(title)
        builder.setPositiveButton(R.string.accept) { dialog, which ->
            if (action == actionOnError.CLOSE) {
                finish()
            }
            dialog.dismiss()
        }

        builder.create().show()
    }

    private fun showMessageDialog(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
                .setTitle(title)
        builder.setPositiveButton(R.string.accept) { dialog, which -> dialog.dismiss() }

        builder.create().show()
    }

    protected abstract fun getLayoutReference(): Int

    override fun showError(title: String, message: String, action: actionOnError) {
        showErrorDialog(title, message, action)
    }


    override fun showMessage(title: String, message: String) {
        showMessageDialog(title, message)
    }


    override fun showLoading() {
        generic_loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        generic_loading.visibility = View.GONE
    }


}
