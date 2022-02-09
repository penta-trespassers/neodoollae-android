package com.pentatrespassers.neodoollae.view.login.main.mypage

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ActivitySettingsBinding
import com.pentatrespassers.neodoollae.lib.Util.setOneLineMenu
import com.pentatrespassers.neodoollae.view.login.main.mypage.settings.DeveloperInfoFragment
import com.pentatrespassers.neodoollae.view.login.main.mypage.settings.ManageAccountFragment
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
    private val manageAccountFragment = ManageAccountFragment.newInstance()

    private fun replaceSettingsFrame(fragment: Fragment) = fragmentTransaction {
        replace(R.id.settingsFrame, fragment)
        with(bind) {
            when (fragment) {
                notificationSettingsFragment -> {
                    titleTextSettings.text = getString(R.string.notification_settings)
                }
                manageAccountFragment -> {
                    titleTextSettings.text = getString(R.string.manage_account)
                }
                termsOfUseFragment -> {
                    titleTextSettings.text = getString(R.string.terms_of_use)
                }
                developerInfoFragment -> {
                    titleTextSettings.text = getString(R.string.developer_info)
                }
            }
            settingsFrame.visibility = View.VISIBLE
            settingsFrame.isClickable = true
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            backButtonSettings.setOnClickListener {
                onBackPressed()
            }

            noticeSettingsCell.setOnClickListener {
                replaceSettingsFrame(notificationSettingsFragment)
            }

            manageAccountCell.setOnClickListener {
                replaceSettingsFrame(manageAccountFragment)
            }

            termsOfUseCell.setOnClickListener {
                replaceSettingsFrame(termsOfUseFragment)
            }

            supportDeveloperCell.setOnClickListener {

            }

            developerInfoCell.setOnClickListener {
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