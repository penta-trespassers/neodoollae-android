package com.pentatrespassers.neodoollae.common.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView




class BadgeImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {


    val paint = Paint()
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {

            paint.color = Color.RED

            canvas.drawCircle(0f, 0f, 1f, paint)
        }
    }
}