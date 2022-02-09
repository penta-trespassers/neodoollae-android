package com.pentatrespassers.neodoollae.view.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.dynamiclinks.PendingDynamicLinkData
import com.google.firebase.dynamiclinks.ktx.androidParameters
import com.google.firebase.dynamiclinks.ktx.dynamicLink
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.dynamiclinks.ktx.socialMetaTagParameters
import com.google.firebase.ktx.Firebase
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ActivityMainBinding
import com.pentatrespassers.neodoollae.lib.Util
import com.pentatrespassers.neodoollae.view.login.main.*
import com.pentatrespassers.neodoollae.view.login.main.home.EntireScheduleActivity
import com.pentatrespassers.neodoollae.view.login.main.mypage.EditMyInfoActivity
import com.pentatrespassers.neodoollae.view.login.main.mypage.SettingsActivity
import splitties.activities.start
import splitties.fragments.fragmentTransaction

class MainActivity : AppCompatActivity() {

    private var currentFragmentIndex = 0
    private var previousFragmentIndex = -1

    private val bind by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val homeFragment by lazy {
        HomeFragment.newInstance()
    }
    private val aroundFragment by lazy {
        AroundFragment.newInstance()
    }
    private val friendFragment by lazy {
        FriendFragment.newInstance(bind.addfriendButton)
    }
    private val reservationFragment by lazy {
        ReservationFragment.newInstance()
    }
    private val myPageFragment by lazy {
        MyPageFragment.newInstance()
    }
    private val notificationFragment by lazy {
        NotificationFragment.newInstance()
    }
    private val fragmentList by lazy {
        arrayListOf(
            homeFragment,
            aroundFragment,
            friendFragment,
            reservationFragment,
            myPageFragment,
            notificationFragment
        )
    }


    private fun showViews(vararg views: View) {
        with(bind) {
            appBarConstraint.visibility = View.VISIBLE
            View.GONE.also {
                backButtonMain.visibility = it
                titleText.visibility = it
                editButton.visibility = it
                notificationButton.visibility = it
                scheduleButton.visibility = it
                settingButton.visibility = it
                addfriendButton.visibility = it
            }
            View.VISIBLE.also {
                for (view in views) {
                    view.visibility = it
                }
            }

        }
    }

    private fun replaceMainFrame(fragment: Fragment) {
        replaceMainFrame(fragmentList.indexOf(fragment))
    }

    private fun replaceMainFrame(index: Int) {
        val currentFragment = fragmentList[currentFragmentIndex]
        val nextFragment = fragmentList[index]


        fragmentTransaction {
            hide(currentFragment)
            with(bind) {
                when (nextFragment) {
                    homeFragment -> {
                        titleText.text = getString(R.string.home)
                        showViews(titleText, notificationButton, scheduleButton)
                    }
                    friendFragment -> {
                        titleText.text = getString(R.string.friend)
                        showViews(titleText, addfriendButton, notificationButton)
                    }
                    reservationFragment -> {
                        titleText.text = getString(R.string.reservation_history)
                        showViews(titleText)
                    }
                    myPageFragment -> {
                        titleText.text = getString(R.string.my_page)
                        showViews(titleText, editButton, notificationButton, settingButton)
                    }
                    notificationFragment -> {
                        titleText.text = getString(R.string.notification)
                        showViews(backButtonMain, titleText)
                    }
                    else -> appBarConstraint.visibility = View.GONE
                }
            }

            show(nextFragment)
            previousFragmentIndex = currentFragmentIndex
            currentFragmentIndex = index
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Neodoollae)
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)
            val dynamicLink = Firebase.dynamicLinks.dynamicLink {
                link = Uri.parse("https://neodoollae.page.com/진하")
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
            Firebase.dynamicLinks
                .getDynamicLink(intent)
                .addOnSuccessListener(this@MainActivity) { data: PendingDynamicLinkData? ->
                    data?.link?.lastPathSegment?.let {
                        Util.j(it)
                    }
                }
            fragmentTransaction {
                for (i in fragmentList.indices) {
                    add(R.id.mainFrame, fragmentList[i])
                    if (i != 0) {
                        hide(fragmentList[i])
                    }
                }

            }
            editButton.setOnClickListener{
                start<EditMyInfoActivity>()
            }
            notificationButton.setOnClickListener {
                replaceMainFrame(5)
            }
            backButtonMain.setOnClickListener {
                replaceMainFrame(previousFragmentIndex)
            }
            settingButton.setOnClickListener {
                start<SettingsActivity>()
            }

            scheduleButton.setOnClickListener {
                start<EntireScheduleActivity>()
            }

            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.homeItem -> {
                        replaceMainFrame(homeFragment)
                    }
                    R.id.aroundItem -> {
                        replaceMainFrame(aroundFragment)
                    }
                    R.id.friendItem -> {
                        replaceMainFrame(friendFragment)
                    }
                    R.id.reservationItem -> {
                        replaceMainFrame(reservationFragment)
                    }
                    R.id.myPageItem -> {
                        replaceMainFrame(myPageFragment)
                    }
                }
                true
            }
        }
    }

    override fun onBackPressed() {
        with(bind) {
            if (backButtonMain.visibility == View.VISIBLE) {
                backButtonMain.performClick()
            } else {
                super.onBackPressed()
            }
        }

    }

}