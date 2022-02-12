package com.pentatrespassers.neodoollae.view.login.main

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.dynamiclinks.ktx.androidParameters
import com.google.firebase.dynamiclinks.ktx.dynamicLink
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.dynamiclinks.ktx.socialMetaTagParameters
import com.google.firebase.ktx.Firebase
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.R.string.my_code_cell
import com.pentatrespassers.neodoollae.databinding.FragmentMyPageBinding
import com.pentatrespassers.neodoollae.lib.Authentication
import com.pentatrespassers.neodoollae.lib.Util.setOneLineMenu
import com.pentatrespassers.neodoollae.view.login.main.mypage.ShowImageActivity
import splitties.bundle.putExtras
import splitties.fragments.start

class MyPageFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentMyPageBinding

    private lateinit var clipData: ClipData
    private lateinit var clipboardManager: ClipboardManager

    private val user
        get() = Authentication.user

    private fun reloadInformation() {
        with(bind) {
            myPageProfileView.setProfileView(user)
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



            myCodeCell.setOnClickListener {

            }

            manageReviewCell.setOnClickListener {

            }

            visitHistoryCell.setOnClickListener {

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
                    val dynamicLink = Firebase.dynamicLinks.dynamicLink {
                        link = Uri.parse("https://neodoollae.page.com/${user?.friendCode}")
                        domainUriPrefix = "https://neodoollae.page.link/"
                        androidParameters {
                            fallbackUrl = Uri.parse("https://google.com")
                        }
                        socialMetaTagParameters {
                            title = "제목"
                            description = "설명"
                            imageUrl =
                                Uri.parse("https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTEyMDNfMTc5%2FMDAxNjM4NTI1OTg5Njgw.7piDrxLvr8aJhZnVfGXO-AzMwg6VD5WfBy839ByV4M4g.GWnAUlG2Cz2dTbioPYYlnh6gXnMyGVVrB6RqXXLFNikg.JPEG.hihaho57%2F20211203%25A3%25DF190531.jpg&type=sc960_832")
                        }
                    }
                    startActivity(Intent.createChooser(Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, dynamicLink.uri.toString())
                        type = "text/plain"
                    }, "Share"))
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