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
    private val fragmentList =
        arrayListOf(homeFragment, aroundFragment, friendFragment, reservationFragment, myPageFragment)

    private fun replaceMainFrame(index: Int) {
        val fragment = fragmentList[index]
        fragmentTransaction {
            hide(fragmentList[currentFragmentIndex])
            with(bind) {
                when (fragment) {
                    homeFragment -> {
                        settingButton.visibility = View.GONE
                        menuBarConstraint.visibility = View.VISIBLE
                    }
                    myPageFragment -> {
                        settingButton.visibility = View.VISIBLE
                        menuBarConstraint.visibility = View.VISIBLE
                    }
                    else -> menuBarConstraint.visibility = View.GONE
                }
            }

            show(fragment)
            currentFragmentIndex = index
        }
    }

    private var currentFragmentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Neodoollae)
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)
            fragmentTransaction() {
                for (i in fragmentList.indices) {
                    add(R.id.mainFrame, fragmentList[i])
                    if (i != 0) {
                        hide(fragmentList[i])
                    }
                }
            }
            notificationButton.setOnClickListener {

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


}