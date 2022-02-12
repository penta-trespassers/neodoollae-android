package com.pentatrespassers.neodoollae.view.login.main

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.R.string.my_code_cell
import com.pentatrespassers.neodoollae.databinding.FragmentMyPageBinding
import com.pentatrespassers.neodoollae.lib.Authentication
import com.pentatrespassers.neodoollae.lib.Util.setOneLineMenu
import com.pentatrespassers.neodoollae.view.login.main.friend.friendlist.friendprofile.ReviewActivity
import com.pentatrespassers.neodoollae.view.login.main.mypage.ManageReviewActivity
import com.pentatrespassers.neodoollae.view.login.main.mypage.ShowImageActivity
import com.pentatrespassers.neodoollae.view.login.main.mypage.VisitHistoryActivity
import splitties.bundle.putExtras
import splitties.fragments.start
import splitties.toast.toast

class MyPageFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentMyPageBinding

    private lateinit var clipData: ClipData
    private lateinit var clipboardManager: ClipboardManager

    private val user = Authentication.user!!

    private fun reloadInformation() {
        with(bind) {
            Glide.with(this@MyPageFragment).load(user.profileImage)
                .error(R.drawable.ic_common_account_no_padding)
                .into(myPageProfileView.profileImage)

            myPageProfileView.nameText.text = user.nickname

            with(myCodeCell) {
                oneLineMenuImage.setImageResource(R.drawable.ic_mypage_copy)
                oneLineMenuText.text = resources.getString(my_code_cell) + user.friendCode
                clipData = ClipData.newPlainText("friend code", user.friendCode)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentMyPageBinding.inflate(inflater, container, false)
        with(bind) {
            reloadInformation()

            with(myPageProfileView) {
                profileImage.setOnClickListener {
                    start<ShowImageActivity> {
                        putExtras(ShowImageActivity.Extras) {
                            this.profileImage = user.profileImage
                        }
                    }
                }
                guestScoreButton.setOnClickListener {
                    start<ReviewActivity> {
                        putExtras(ReviewActivity.Extras) {
                            this.user = this@MyPageFragment.user
                        }
                    }
                }
                hostScoreButton.setOnClickListener {
                    start<ReviewActivity> {
                        putExtras(ReviewActivity.Extras) {
                            this.user = this@MyPageFragment.user
                        }
                    }
                }
            }

            myPageProfileView.profileImage.setOnClickListener {
                start<ShowImageActivity> {
                    putExtras(ShowImageActivity.Extras) {
                        this.profileImage = user.profileImage
                    }
                }
            }

            with(myCodeCell) {
                oneLineMenuConstraint.setOnClickListener {
                    clipboardManager =
                        context?.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                    clipboardManager.setPrimaryClip(clipData)
                    toast(R.string.copy_to_clipboard)
                }
            }

            with(manageReviewCell) {
                setOneLineMenu(this, R.drawable.ic_mypage_review, R.string.manage_review)
                oneLineMenuConstraint.setOnClickListener {
                    start<ManageReviewActivity>()
                }
            }

            with(visitHistoryCell) {
                setOneLineMenu(this, R.drawable.ic_mypage_inventory, R.string.visit_history)
                oneLineMenuConstraint.setOnClickListener {
                    start<VisitHistoryActivity>()
                }
            }

            return root
        }
    }

    companion object {
        fun newInstance() = MyPageFragment()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            Authentication.refreshUser()
        } else {
            reloadInformation()
        }
    }


}