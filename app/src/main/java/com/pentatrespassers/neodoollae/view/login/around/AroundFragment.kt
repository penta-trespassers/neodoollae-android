package com.pentatrespassers.neodoollae.view.login.around

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.FragmentAroundBinding

class AroundFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentAroundBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentAroundBinding.inflate(inflater, container, false)
        with(bind) {
            return root
        }
        //val mapfragment = supportFragmentManager.findFragmentById(R.id.mapViewAround) as MapFragment

        //왜 인식을 못할까용?? map이 fragment인데 왜 bind에 뜨지 않을까요?? 저는 fragment를 써본 적이 없답니다...ㅠ ㅁ ㅠ
        bind.AroundLayout.setOnClickListener{
            bind.mapSearchViewAround.visibility = View.VISIBLE
        }





    }





    companion object {
        fun newInstance() = AroundFragment()
    }
}

