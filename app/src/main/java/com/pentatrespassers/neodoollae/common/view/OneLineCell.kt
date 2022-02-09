package com.pentatrespassers.neodoollae.common.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellOneLineBinding
import com.pentatrespassers.neodoollae.lib.Util.gone
import com.pentatrespassers.neodoollae.lib.Util.show


class OneLineCell @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var imageResource: Drawable? = null
    var isSwitchSetting = false
    var isTextSetting = false
    var mainText = ""
    var settingText = ""

    init {
        initView()

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.OneLineCell,
            0, 0
        ).apply {
            try {
                imageResource = getDrawable(R.styleable.OneLineCell_imageResource)
                isSwitchSetting = getBoolean(R.styleable.OneLineCell_isSwitchSetting, false)
                isTextSetting = getBoolean(R.styleable.OneLineCell_isTextSetting, false)
                mainText = getString(R.styleable.OneLineCell_mainText).toString()
                settingText = getString(R.styleable.OneLineCell_settingText).toString()
            } finally {
                recycle()
            }

        }


    }

    private fun initView() {
        View.inflate(context, R.layout.cell_one_line, this)

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        with(CellOneLineBinding.bind(this)) {
            oneLineMainText.text = mainText
            when {
                isSwitchSetting -> {
                    oneLineImage.gone()
                    oneLineSettingSwitch.show()
                    oneLineSettingText.gone()
                    oneLineMenuConstraint.setOnClickListener {
                        oneLineSettingSwitch.toggle()
                    }
                }
                isTextSetting -> {
                    oneLineImage.gone()
                    oneLineSettingSwitch.gone()
                    oneLineSettingText.show()
                    oneLineSettingText.text = settingText
                }
                else -> {
                    oneLineImage.show()
                    oneLineSettingSwitch.gone()
                    oneLineSettingText.gone()
                    oneLineImage.setImageDrawable(imageResource)
                }
            }
        }
    }
}