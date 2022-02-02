package com.pentatrespassers.neodoollae.lib


import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.pentatrespassers.neodoollae.databinding.CellOneLineMenuBinding
import com.pentatrespassers.neodoollae.databinding.CellTwoLineBinding
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

    fun j(msg: Any?) = Log.d("jinha", "$msg")

    fun getDateFormatter(pattern: String) = SimpleDateFormat(pattern, Locale.getDefault())
    fun getDateFormat(obj: Any, pattern: String) = SimpleDateFormat(pattern, Locale.getDefault()).format(obj)
    var simpleDateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())


    fun setOneLineMenu(cell: CellOneLineMenuBinding, image: Int, mainText: Int) {
        with(cell) {
            oneLineMenuImage.setImageResource(image)
            oneLineMenuText.setText(mainText)
        }
    }
    fun setTwoLineCell(cell: CellTwoLineBinding, image: Int, mainText: Int, subText: Int){
        with(cell){
            twoLineImage.setImageResource(image)
            twoLineMainText.setText(mainText)
            twoLineSubText.setText(subText)
        }
    }
}