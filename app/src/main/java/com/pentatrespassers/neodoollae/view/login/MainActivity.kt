package com.pentatrespassers.neodoollae.view.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ActivityMainBinding
import com.pentatrespassers.neodoollae.view.login.main.*
import splitties.fragments.fragmentTransaction

class MainActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val homeFragment by lazy {
        HomeFragment.newInstance(23)
    }
    private val aroundFragment by lazy {
        AroundFragment.newInstance()
    }
    private val friendFragment by lazy {
        FriendFragment.newInstance()
    }
    private val noticeFragment by lazy {
        NoticeFragment.newInstance()
    }
    private val myPageFragment by lazy {
        MyPageFragment.newInstance()
    }
    private val fragmentList =
        arrayListOf(homeFragment, aroundFragment, friendFragment, noticeFragment, myPageFragment)

    private fun replaceMainFrame(index: Int) = fragmentTransaction {
        hide(fragmentList[currentFragmentIndex])
        show(fragmentList[index])
        currentFragmentIndex = index
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
                    R.id.noticeItem -> {
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