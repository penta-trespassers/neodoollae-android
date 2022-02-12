package com.pentatrespassers.neodoollae.view.login.main.mypage

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ActivityManageReviewBinding
import com.pentatrespassers.neodoollae.view.login.main.mypage.managereview.WritableReviewFragment
import com.pentatrespassers.neodoollae.view.login.main.mypage.managereview.WrittenReviewFragment
import splitties.fragments.fragmentTransaction

class ManageReviewActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityManageReviewBinding.inflate(layoutInflater)
    }

    private val writableReviewFragment = WritableReviewFragment.newInstance()
    private val writtenReviewFragment = WrittenReviewFragment.newInstance()


    @SuppressLint("UnsafeOptInUsageError")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)

            fragmentTransaction {
                add(R.id.manageReviewFrame, writableReviewFragment)
                add(R.id.manageReviewFrame, writtenReviewFragment)
                hide(writtenReviewFragment)
            }
            writableConstraint.setOnClickListener {
                fragmentTransaction {
                    hide(writtenReviewFragment)
                    show(writableReviewFragment)
                    writableText.setTextColor(
                        ContextCompat.getColor(
                            root.context,
                            R.color.trespassBlue_900
                        )
                    )
                    writableUnderlineConstraint.visibility = View.VISIBLE
                    writtenText.setTextColor(
                        ContextCompat.getColor(
                            root.context,
                            R.color.trespassGray_900
                        )
                    )
                    writtenUnderlineConstraint.visibility = View.GONE
                }
            }
            writtenConstraint.setOnClickListener {
                fragmentTransaction {
                    hide(writableReviewFragment)
                    show(writtenReviewFragment)
                    writtenText.setTextColor(
                        ContextCompat.getColor(
                            root.context,
                            R.color.trespassBlue_900
                        )
                    )
                    writtenUnderlineConstraint.visibility = View.VISIBLE
                    writableText.setTextColor(
                        ContextCompat.getColor(
                            root.context,
                            R.color.trespassGray_900
                        )
                    )
                    writableUnderlineConstraint.visibility = View.GONE
                }
            }

            val leftBadge = BadgeDrawable.create(this@ManageReviewActivity).apply {
                number = 3
                backgroundColor =
                    ContextCompat.getColor(this@ManageReviewActivity, R.color.app_theme)
                badgeTextColor = ContextCompat.getColor(this@ManageReviewActivity, R.color.white)
                badgeGravity = BadgeDrawable.TOP_END
                writableReviewBadge.foreground = this
                writableReviewBadge.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
                    BadgeUtils.attachBadgeDrawable(this, writableText, writableReviewBadge)
                }
            }


            val rightBadge = BadgeDrawable.create(this@ManageReviewActivity).apply {
                number = 3
                backgroundColor =
                    ContextCompat.getColor(this@ManageReviewActivity, R.color.app_theme)
                badgeTextColor = ContextCompat.getColor(this@ManageReviewActivity, R.color.white)
                badgeGravity = BadgeDrawable.TOP_END
                writtenBadge.foreground = this
                writtenBadge.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
                    BadgeUtils.attachBadgeDrawable(this, writtenText, writtenBadge)
                }
            }

            backButtonReview.setOnClickListener {
                finish()
            }
        }

    }

}