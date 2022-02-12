package com.pentatrespassers.neodoollae.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.isDigitsOnly
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellBadgeTextBinding
import com.pentatrespassers.neodoollae.lib.Util.gone
import com.pentatrespassers.neodoollae.lib.Util.hide

class BadgeTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var text = ""

    init {
        initView()

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.BadgeTextView,
            0, 0
        ).apply {
            try {
                text = getString(R.styleable.BadgeTextView_badgeText).toString()
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
            if(text.isDigitsOnly() && text.toInt() != 0){
                badgeText.text = this@BadgeTextView.text
            } else {
                root.gone()
            }
        }
    }
}