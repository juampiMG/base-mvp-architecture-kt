package com.jp.app.utils

import android.app.Activity
import android.app.Fragment
import com.jp.app.common.view.BaseFragment
import com.jp.app.model.SampleView

object NavigationUtils {
    @JvmStatic
    fun navigationToSampleInfoActivity(activity: Activity, sample: SampleView) {
        //        Bundle extras = new Bundle();
        //        extras.putParcelable(SampleView.class.getSimpleName(), Parcels.wrap(sample));
        //        Intent intent = new Intent(activity, SampleInfoActivity.class);
        //        intent.putExtras(extras);
        //        activity.startActivity(intent);
    }

    @JvmStatic
    fun navigateToFragment(activity: Activity, fragment: Fragment,
                           contentFrame: Int, addToBackStack: Boolean) {
        pushFragment(activity, fragment, contentFrame, addToBackStack)
    }

    private fun getFragmentTag(fragment: Fragment): String {
        return (fragment as? BaseFragment<*, *>)?.getFragmentId() ?: (fragment as Any).javaClass.name
    }


    private fun pushFragment(activity: Activity, fragment: Fragment?, contentFrame: Int, addToBackStack: Boolean) {
        if (fragment == null) {
            return
        }

        val fragmentTransaction = activity.fragmentManager
                .beginTransaction()

        val tag = getFragmentTag(fragment)

        fragmentTransaction.replace(contentFrame, fragment, tag)

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(tag)
        }

        fragmentTransaction.commit()
        // Only calling executePendingTransactions() if no nested Fragment call
        if (contentFrame <= 0) {
            activity.fragmentManager.executePendingTransactions()
        }

    }
}
