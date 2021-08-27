package com.pentatrespassers.neodoollae.lib

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

object Util {
    inline fun Fragment.fragmentTransaction(
        now: Boolean = true,
        allowStateLoss: Boolean = false,
        transactionBody: FragmentTransaction.() -> Unit
    ): Unit = childFragmentManager.beginTransaction().apply(transactionBody).let { ft ->
        when {
            allowStateLoss -> if (now) ft.commitNowAllowingStateLoss() else ft.commitAllowingStateLoss()
            else -> if (now) ft.commitNow() else ft.commit()
        }
    }
    fun j(msg: String) = Log.d("jinha", msg)
    fun dummy(any: Any?) = any
}