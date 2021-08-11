package com.instructor.nedoollae.view.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.instructor.nedoollae.R
import com.instructor.nedoollae.databinding.ActivityMainBinding
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

    private fun replaceMainFrame(fragment: Fragment) = fragmentTransaction {
        replace(R.id.mainFrame, fragment)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)
            replaceMainFrame(homeFragment)
            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.homeItem -> {
                        replaceMainFrame(homeFragment)
                    }
                    R.id.aroundItem -> {
                        replaceMainFrame(aroundFragment)
                    }
                }
                true
            }
        }
    }


}