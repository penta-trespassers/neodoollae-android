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
import com.pentatrespassers.neodoollae.view.login.main.mypage.ShowImageActivity
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

            myCodeCell.mainText = resources.getString(my_code_cell) + user.friendCode
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

            myCodeCell.setOnClickListener {

            }

            manageReviewCell.setOnClickListener {

            }

            visitHistoryCell.setOnClickListener {

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