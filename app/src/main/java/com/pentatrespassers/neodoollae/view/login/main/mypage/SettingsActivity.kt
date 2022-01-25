package com.pentatrespassers.neodoollae.view.login.main.mypage

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ActivitySettingsBinding
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

            noticeSettingsCell.oneLineSettingImage.setImageResource(R.drawable.ic_notifications_black_24dp)
            noticeSettingsCell.oneLineSettingText.setText(R.string.notification_settings)

            manageAccountCell.oneLineSettingImage.setImageResource(R.drawable.ic_baseline_manage_accounts_24)
            manageAccountCell.oneLineSettingText.setText(R.string.manage_account)

            termsOfUseCell.oneLineSettingImage.setImageResource(R.drawable.ic_receipt_long_black_24dp)
            termsOfUseCell.oneLineSettingText.setText(R.string.terms_of_use)

            supportDeveloperCell.oneLineSettingImage.setImageResource(R.drawable.ic_paid_black_24dp)
            supportDeveloperCell.oneLineSettingText.setText(R.string.support_developer)

            developerInfoCell.oneLineSettingImage.setImageResource(R.drawable.ic_groups_black_24dp)
            developerInfoCell.oneLineSettingText.setText(R.string.developer_info)

            backButtonSettings.setOnClickListener {
                onBackPressed()
            }
            noticeSettingsConstraint.setOnClickListener {
                replaceSettingsFrame(notificationSettingsFragment)
            }
            manageAccountConstraint.setOnClickListener {
                replaceSettingsFrame(manageAccountFragment)
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