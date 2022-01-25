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
                twoLineImage.setImageResource(R.drawable.ic_groups_black_24dp)
                twoLineMainText.setText(R.string.team_name)
                twoLineSubText.setText(R.string.team_description)

            }
            with(firstMemberCell){
                twoLineImage.setImageResource(R.drawable.ic_account_circle_black_24dp)
                twoLineMainText.setText(R.string.first_member)
                twoLineSubText.setText(R.string.back_end)
            }
            with(secondMemberCell){
                twoLineImage.setImageResource(R.drawable.ic_account_circle_black_24dp)
                twoLineMainText.setText(R.string.second_member)
                twoLineSubText.setText(R.string.front_end_designer)
            }
            with(thirdMemberCell){
                twoLineImage.setImageResource(R.drawable.ic_account_circle_black_24dp)
                twoLineMainText.setText(R.string.third_member)
                twoLineSubText.setText(R.string.front_end)
            }
            with(fourthMemberCell){
                twoLineImage.setImageResource(R.drawable.ic_account_circle_black_24dp)
                twoLineMainText.setText(R.string.fourth_member)
                twoLineSubText.setText(R.string.front_back_end)
            }
            with(fifthMemberCell){
                twoLineImage.setImageResource(R.drawable.ic_account_circle_black_24dp)
                twoLineMainText.setText(R.string.fifth_member)
                twoLineSubText.setText(R.string.front_end)
            }

            teamConstraint.setOnClickListener{
                intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/penta-trespassers"))
                startActivity(intent)
            }

            firstMemberConstraint.setOnClickListener{
                intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/sjwhole"))
                startActivity(intent)
            }

            secondMemberConstraint.setOnClickListener{
                intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/inticoy"))
                startActivity(intent)
            }

            thirdMemberConstraint.setOnClickListener{
                intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/SEOJIN-LEE"))
                startActivity(intent)
            }

            fourthMemberConstraint.setOnClickListener{
                intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/recsater"))
                startActivity(intent)
            }

            fifthMemberConstraint.setOnClickListener{
                intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/sunny5875"))
                startActivity(intent)
            }

            return root
        }
    }

    companion object {
        fun newInstance() = DeveloperInfoFragment().apply {
        }
    }


}