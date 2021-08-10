package com.instructor.nedoollae.view.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)
            fragmentTransaction {
                replace(R.id.mainFrame, homeFragment)
            }
            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.homeItem -> {
                        fragmentTransaction {
                            replace(R.id.mainFrame, homeFragment)
                        }
                    }
                    R.id.aroundItem -> {
                        fragmentTransaction {
                            replace(R.id.mainFrame, aroundFragment)
                        }
                    }
                }
                true
            }
        }
    }


}