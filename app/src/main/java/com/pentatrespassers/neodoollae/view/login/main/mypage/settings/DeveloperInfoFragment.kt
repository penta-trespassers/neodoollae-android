package com.pentatrespassers.neodoollae.view.login.main.mypage.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.FragmentDeveloperInfoBinding
import com.pentatrespassers.neodoollae.lib.Util.setTwoLineCell

class DeveloperInfoFragment constructor() : Fragment() {

    private lateinit var bind: FragmentDeveloperInfoBinding
    private lateinit var intent: Intent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentDeveloperInfoBinding.inflate(inflater, container, false)
        with(bind) {
            teamCell.setOnClickListener { hyperlink(R.string.team_url) }
            firstMemberCell.setOnClickListener { hyperlink(R.string.first_url) }
            secondMemberCell.setOnClickListener { hyperlink(R.string.second_url) }
            thirdMemberCell.setOnClickListener { hyperlink(R.string.third_url) }
            fourthMemberCell.setOnClickListener { hyperlink(R.string.fourth_url) }
            fifthMemberCell.setOnClickListener { hyperlink(R.string.fifth_url) }
            return root
        }
    }

    companion object {
        fun newInstance() = DeveloperInfoFragment().apply {
        }
    }

    private fun hyperlink(resource: Int) {
        var url = resources.getString(resource)
        println(url)
        intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

}