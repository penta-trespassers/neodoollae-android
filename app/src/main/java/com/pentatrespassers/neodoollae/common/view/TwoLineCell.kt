package com.pentatrespassers.neodoollae.common.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellOneLineBinding
import com.pentatrespassers.neodoollae.databinding.CellTwoLineBinding
import com.pentatrespassers.neodoollae.lib.Util.gone
import com.pentatrespassers.neodoollae.lib.Util.show


class TwoLineCell @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var imageResource: Drawable? = null
    var mainText = ""
    var subText = ""

    init {
        initView()

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TwoLineCell,
            0, 0
        ).apply {
            try {
                imageResource = getDrawable(R.styleable.TwoLineCell_twoLineImageResource)
                mainText = getString(R.styleable.TwoLineCell_twoLineMainText).toString()
                subText = getString(R.styleable.TwoLineCell_twoLineSubText).toString()
            } finally {
                recycle()
            }

        }


    }

    private fun initView() {
        View.inflate(context, R.layout.cell_two_line, this)

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        with(CellTwoLineBinding.bind(this)) {
            twoLineImage.setImageDrawable(imageResource)
            twoLineMainText.text = mainText
            twoLineSubText.text = subText
        }
    }
}