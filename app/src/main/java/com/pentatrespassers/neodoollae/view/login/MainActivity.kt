package com.pentatrespassers.neodoollae.view.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ActivityMainBinding
import com.pentatrespassers.neodoollae.view.login.main.*
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
        FriendFragment.newInstance()
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
    private val fragmentList =
        arrayListOf(
            homeFragment,
            aroundFragment,
            friendFragment,
            reservationFragment,
            myPageFragment,
            notificationFragment
        )

    private fun replaceMainFrame(index: Int) {
        val currentFragment = fragmentList[currentFragmentIndex]
        val nextFragment = fragmentList[index]
        fragmentTransaction {
            hide(currentFragment)
            with(bind) {
                when (nextFragment) {
                    homeFragment -> {
                        notificationButton.visibility = View.VISIBLE
                        backButtonMain.visibility = View.GONE
                        titleText.visibility = View.GONE
                        settingButton.visibility = View.GONE
                        appBarConstraint.visibility = View.VISIBLE
                    }
                    myPageFragment -> {
                        notificationButton.visibility = View.VISIBLE
                        backButtonMain.visibility = View.GONE
                        titleText.visibility = View.VISIBLE
                        titleText.text = getString(R.string.my_page)
                        settingButton.visibility = View.VISIBLE
                        appBarConstraint.visibility = View.VISIBLE
                    }
                    notificationFragment -> {
                        notificationButton.visibility = View.GONE
                        backButtonMain.visibility = View.VISIBLE
                        titleText.visibility = View.VISIBLE
                        titleText.text = getString(R.string.notification)
                        settingButton.visibility = View.GONE
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
            fragmentTransaction {
                for (i in fragmentList.indices) {
                    add(R.id.mainFrame, fragmentList[i])
                    if (i != 0) {
                        hide(fragmentList[i])
                    }
                }

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

            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.homeItem -> {
                        replaceMainFrame(0)
                    }
                    R.id.aroundItem -> {
                        replaceMainFrame(1)
                    }
                    R.id.friendItem -> {
                        replaceMainFrame(2)
                    }
                    R.id.reservationItem -> {
                        replaceMainFrame(3)
                    }
                    R.id.myPageItem -> {
                        replaceMainFrame(4)
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