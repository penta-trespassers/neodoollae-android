package com.pentatrespassers.neodoollae.view.login.main.reservation

import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import com.pentatrespassers.neodoollae.lib.Util.gone
import com.pentatrespassers.neodoollae.lib.Util.show

class ToggleAnimation {

    companion object {

        fun toggleArrow(view: View, isExpanded: Boolean): Boolean {
            if (isExpanded) {
                view.animate().setDuration(200).rotation(0f)
                return true
            } else {
                view.animate().setDuration(200).rotation(0f)
                return false
            }
        }

        fun expand(view: View, view2: View? = null) {
            view.measure(ViewGroup.LayoutParams.MATCH_PARENT, View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
            val actualHeight = view.measuredHeight
            view.layoutParams.height = 0
            view.show()


            val animation = object : Animation() {
                override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                    view.layoutParams.height = if (interpolatedTime == 1f) ViewGroup.LayoutParams.WRAP_CONTENT
                    else (actualHeight * interpolatedTime).toInt()
                    view.requestLayout()
                    view2?.gone()
                    view2?.show()
                }
            }

            animation.duration = (actualHeight / view.context.resources.displayMetrics.density).toLong()
            view.startAnimation(animation)
        }

        fun collapse(view: View) {
            val actualHeight = view.measuredHeight

            val animation = object : Animation() {
                override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                    if (interpolatedTime == 1f) {
                        view.visibility = View.GONE
                    } else {
                        view.layoutParams.height = (actualHeight - (actualHeight * interpolatedTime)).toInt()
                        view.requestLayout()
                    }
                }
            }

            animation.duration = (actualHeight / view.context.resources.displayMetrics.density).toLong()
            view.startAnimation(animation)
        }
    }

}