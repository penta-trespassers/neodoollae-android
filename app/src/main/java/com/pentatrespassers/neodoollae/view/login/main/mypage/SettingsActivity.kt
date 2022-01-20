package com.pentatrespassers.neodoollae.view.login.main.mypage

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ActivitySettingsBinding
import com.pentatrespassers.neodoollae.view.login.main.mypage.settings.DeveloperInfoFragment
import com.pentatrespassers.neodoollae.view.login.main.mypage.settings.NotificationSettingsFragment
import com.pentatrespassers.neodoollae.view.login.main.mypage.settings.TermsOfUseFragment
import splitties.fragments.fragmentTransaction

class SettingsActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivitySettingsBinding.inflate(layoutInflater)
    }

    private val notificationSettingsFragment = NotificationSettingsFragment.newInstance()
    private val termsOfUseFragment = TermsOfUseFragment.newInstance()
    private val developerInfoFragment = DeveloperInfoFragment.newInstance()

    private fun replaceSettingsFrame(fragment: Fragment) = fragmentTransaction {
        replace(R.id.settingsFrame, fragment)
        with(bind) {
            when (fragment) {
                notificationSettingsFragment -> {
                    titleTextSettings.text = getString(R.string.notification_settings)
                }
                termsOfUseFragment -> {
                    titleTextSettings.text = getString(R.string.terms_of_use)
                }
                developerInfoFragment -> {
                    titleTextSettings.text = getString(R.string.developer_info)
                }
            }
            settingsFrame.visibility = View.VISIBLE
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            backButtonSettings.setOnClickListener {
                onBackPressed()
            }
            noticeSettingsConstraint.setOnClickListener {
                replaceSettingsFrame(notificationSettingsFragment)
            }
            termsOfUseConstraint.setOnClickListener {
                replaceSettingsFrame(termsOfUseFragment)
            }
            developerInfoConstraint.setOnClickListener {
                replaceSettingsFrame(developerInfoFragment)
            }

            setContentView(root)
        }
    }

    override fun onBackPressed() {
        with(bind) {
            if (settingsFrame.visibility == View.VISIBLE) {
                titleTextSettings.text = getString(R.string.settings)
                settingsFrame.visibility = View.GONE
            } else {
                finish()
            }
        }

    }
}