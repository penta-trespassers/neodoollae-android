package com.pentatrespassers.neodoollae.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellOneLineMenuBinding


class BadgeImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {


    var mShowText = false


    init {
        initView()

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.BadgeImageView,
            0, 0).apply {

            try {
                mShowText = getBoolean(R.styleable.BadgeImageView_showText, false)
            } finally {
                recycle()
            }
        }
    }

    private fun initView() {
        View.inflate(context, R.layout.cell_one_line_menu, this)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        with(CellOneLineMenuBinding.bind(this)) {
            oneLineMenuText.text = "$mShowText"
        }
    }
}