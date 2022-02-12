package com.pentatrespassers.neodoollae.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellBadgeTextBinding

class BadgeTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var badgeText = ""

    init {
        initView()

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.BadgeTextView,
            0, 0
        ).apply {
            try {
                badgeText = getString(R.styleable.BadgeTextView_badgeText).toString()
            } finally {
                recycle()
            }
        }
    }

    private fun initView() {
        View.inflate(context, R.layout.cell_badge_text, this)

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        with(CellBadgeTextBinding.bind(this)) {
            badgeText.text = this@BadgeTextView.badgeText
        }
    }
}