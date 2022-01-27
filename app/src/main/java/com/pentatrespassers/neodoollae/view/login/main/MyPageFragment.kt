package com.pentatrespassers.neodoollae.view.login.main

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.R.string.my_code
import com.pentatrespassers.neodoollae.R.string.my_code_cell
import com.pentatrespassers.neodoollae.databinding.FragmentMyPageBinding
import com.pentatrespassers.neodoollae.lib.Authentication
import com.pentatrespassers.neodoollae.lib.Util.setOneLineSetting
import splitties.toast.toast

class MyPageFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentMyPageBinding

    private lateinit var clipData : ClipData
    private lateinit var clipboardManager : ClipboardManager

    private fun reloadInformation() {
        with(bind) {
            val user = Authentication.user!!
            Glide.with(this@MyPageFragment).load(user.profileImage)
                .error(R.drawable.ic_baseline_account_circle_24)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        myPageProfileView.profileImage.setColorFilter(R.color.trespassGray_600)
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        myPageProfileView.profileImage.imageTintList = null
                        return false
                    }

                })
                .into(myPageProfileView.profileImage)

            myPageProfileView.nameText.text = user.nickname

            with(myCodeCell){
                oneLineSettingImage.setImageResource(R.drawable.ic_mypage_copy)
                oneLineSettingText.text = resources.getString(my_code_cell) + user.friendCode
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

            with(myCodeCell){
                oneLineSettingConstraint.setOnClickListener {
                    clipboardManager = context?.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                    clipboardManager.setPrimaryClip(clipData)
                    toast(R.string.copy_to_clipboard)
                }
            }

            with(manageReviewCell){
                setOneLineSetting(this, R.drawable.ic_mypage_review, R.string.manage_review)
                oneLineSettingConstraint.setOnClickListener {
                    // TODO : MANAGE REVIEWS
                }
            }

            with(visitHistoryCell) {
                setOneLineSetting(this, R.drawable.ic_mypage_inventory, R.string.visit_history)
                oneLineSettingConstraint.setOnClickListener {
                    // TODO : VISIT HISTORY
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