package com.pentatrespassers.neodoollae.common.view

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.pentatrespassers.neodoollae.R

class ProfileView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init{
        inflate(context, R.layout.custom_profile_view, this)
    }
}