package com.pentatrespassers.neodoollae.lib


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Outline
import android.graphics.drawable.GradientDrawable
import android.location.Location
import android.location.LocationManager
import android.util.Log
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.annotation.Size
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.pentatrespassers.neodoollae.databinding.CellOneLineMenuBinding
import com.pentatrespassers.neodoollae.databinding.CellTwoLineBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.*

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

    fun ImageView.setImageCornerRadius(radius: Float) {
        this.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View?, outline: Outline?) {
                outline?.setRoundRect(0, 0, view!!.width, view.height, radius)
            }
        }
        this.clipToOutline = true
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
    fun getDateFormat(obj: Any, pattern: String): String =
        SimpleDateFormat(pattern, Locale.getDefault()).format(obj)

    var simpleDateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())


    fun setOneLineMenu(cell: CellOneLineMenuBinding, image: Int, mainText: Int) {
        with(cell) {
            oneLineMenuImage.setImageResource(image)
            oneLineMenuText.setText(mainText)
        }
    }

    fun setTwoLineCell(cell: CellTwoLineBinding, image: Int, mainText: Int, subText: Int) {
        with(cell) {
            twoLineImage.setImageResource(image)
            twoLineMainText.setText(mainText)
            twoLineSubText.setText(subText)
        }
    }


    /**
     * 주어진 문자열의 마지막 문자의 받침 여부에 따라 새로운 문자열을 만듦.
     *
     * @author jinha
     * @param postposition1 받침이 있을 때 붙일 문자열
     * @param postposition2 받침이 없을 때 붙일 문자열
     */
    fun ppString(string: String, postposition1: String, postposition2: String) =
        string + if ((string.last().code - 0xAC00) % 28 > 0) postposition1 else postposition2

    fun getLocation(context: Context): Location? {
        with(context) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                return (getSystemService(Context.LOCATION_SERVICE) as LocationManager?)?.getLastKnownLocation(
                    LocationManager.GPS_PROVIDER)
            }
            return null
        }
    }


    /**
     * 두 좌표의 거리를 계산한다.
     *
     * @param lat1 위도1
     * @param lon1 경도1
     * @param lat2 위도2
     * @param lon2 경도2
     * @return 두 좌표의 거리(m)
     */
    fun getDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2).pow(2.0) + sin(dLon / 2).pow(2.0) * cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2))
        val c = 2 * asin(sqrt(a))
        return 6372.8 * 1000 * c
    }
}