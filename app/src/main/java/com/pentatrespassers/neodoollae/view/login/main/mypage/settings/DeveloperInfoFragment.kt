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

class DeveloperInfoFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentDeveloperInfoBinding
    private lateinit var intent: Intent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentDeveloperInfoBinding.inflate(inflater, container, false)
        with(bind) {
            with(teamCell){
                setTwoLineCell(this, R.drawable.ic_groups_black_24dp, R.string.team_name, R.string.team_description)
                twoLineConstraint.setOnClickListener {
                    hyperlink(R.string.team_url)
                }
            }
            with(firstMemberCell){
                setTwoLineCell(this, R.drawable.ic_account_circle_black_24dp, R.string.first_member, R.string.back_end)
                twoLineConstraint.setOnClickListener {
                    hyperlink(R.string.first_url)
                }
            }
            with(secondMemberCell){
                setTwoLineCell(this, R.drawable.ic_account_circle_black_24dp, R.string.second_member, R.string.front_end_designer)
                twoLineConstraint.setOnClickListener {
                    hyperlink(R.string.second_url)
                }
            }
            with(thirdMemberCell){
                setTwoLineCell(this, R.drawable.ic_account_circle_black_24dp, R.string.third_member, R.string.front_end)
                twoLineConstraint.setOnClickListener {
                    hyperlink(R.string.third_url)
                }
            }
            with(fourthMemberCell){
                setTwoLineCell(this, R.drawable.ic_account_circle_black_24dp, R.string.fourth_member, R.string.front_back_end)
                twoLineConstraint.setOnClickListener {
                    hyperlink(R.string.fourth_url)
                }
            }
            with(fifthMemberCell){
                setTwoLineCell(this, R.drawable.ic_account_circle_black_24dp, R.string.fifth_member, R.string.front_end)
                twoLineConstraint.setOnClickListener {
                    hyperlink(R.string.fifth_url)
                }
            }
            return root
        }
    }

    companion object {
        fun newInstance() = DeveloperInfoFragment().apply {
        }
    }

    private fun hyperlink(resource: Int){
        var url = resources.getString(resource)
        println(url)
        intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

}