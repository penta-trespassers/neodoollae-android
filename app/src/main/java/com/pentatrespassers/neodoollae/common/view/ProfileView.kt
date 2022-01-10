package com.pentatrespassers.neodoollae.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ViewProfileBinding

class ProfileView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init{
        inflate(context, R.layout.view_profile, this)

    }


}