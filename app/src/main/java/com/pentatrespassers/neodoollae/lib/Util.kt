package com.pentatrespassers.neodoollae.lib

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.View
import androidx.annotation.Size
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import java.text.SimpleDateFormat
import java.util.*

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

    fun View.setBackgroundColor(@Size(min = 1) colorString: String) {
        (background as GradientDrawable).setColor(Color.parseColor(colorString))
    }

    fun View.setCornerRadius(radius: Float) {
        (background as? GradientDrawable)?.cornerRadius = radius
    }

    fun View.show() {
        visibility = View.VISIBLE
    }
    fun View.hide() {
        visibility = View.INVISIBLE
    }
    fun View.gone() {
        visibility = View.GONE
    }


    fun j(msg: Any?) = Log.d("jinha", "$msg")

    fun getDateFormatter(pattern: String) = SimpleDateFormat(pattern, Locale.getDefault())
    fun getDateFormat(obj: Any, pattern: String) =
        SimpleDateFormat(pattern, Locale.getDefault()).format(obj)

    var simpleDateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())


    /**
     * 주어진 문자열의 마지막 문자의 받침 여부에 따라 새로운 문자열을 만듦.
     *
     * @author jinha
     * @param postposition1 받침이 있을 때 붙일 문자열
     * @param postposition2 받침이 없을 때 붙일 문자열
     */
    fun ppString(string: String, postposition1: String, postposition2: String) =
        string + if ((string.last().code - 0xAC00) % 28 > 0) postposition1 else postposition2
}