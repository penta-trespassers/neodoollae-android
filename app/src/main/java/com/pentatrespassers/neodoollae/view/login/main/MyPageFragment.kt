package com.pentatrespassers.neodoollae.view.login.main

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
import com.pentatrespassers.neodoollae.databinding.FragmentMyPageBinding
import com.pentatrespassers.neodoollae.lib.Authentication
import com.pentatrespassers.neodoollae.view.login.main.mypage.RoomBookInfoActivity
import com.pentatrespassers.neodoollae.view.login.main.mypage.RoomVisitTraceActivity
import com.pentatrespassers.neodoollae.view.login.main.mypage.UserBookInfoActivity
import com.pentatrespassers.neodoollae.view.login.main.mypage.UserVisitTraceActivity
import splitties.fragments.start

class MyPageFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentMyPageBinding

    private fun reloadInformation() {
        with(bind) {
//            val user = Authentication.user!!
//            Glide.with(this@MyPageFragment).load(user.profileImage)
//                .error(R.drawable.ic_baseline_account_circle_24)
//                .listener(object : RequestListener<Drawable> {
//                    override fun onLoadFailed(
//                        e: GlideException?,
//                        model: Any?,
//                        target: Target<Drawable>?,
//                        isFirstResource: Boolean
//                    ): Boolean {
//                        myProfileImageView.setColorFilter(R.color.gray)
//                        return false
//                    }
//
//                    override fun onResourceReady(
//                        resource: Drawable?,
//                        model: Any?,
//                        target: Target<Drawable>?,
//                        dataSource: DataSource?,
//                        isFirstResource: Boolean
//                    ): Boolean {
//                        myProfileImageView.imageTintList = null
//                        return false
//                    }
//
//                })
//                .into(myProfileImageView)
//            nicknameTextMyPage.text = user.nickname
//            friendCodeText.text = user.friendCode
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentMyPageBinding.inflate(inflater, container, false)
        with(bind) {

//            reloadInformation()
//
//            myRoomHistoryButton.setOnClickListener {
//                start<RoomVisitTraceActivity>()
//            }
//            myHistoryButton.setOnClickListener {
//                start<UserVisitTraceActivity>()
//            }
//            myRoomReservationButton.setOnClickListener {
//                start<RoomBookInfoActivity>()
//            }
//            myReservationButton.setOnClickListener {
//                start<UserBookInfoActivity>()
//            }

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