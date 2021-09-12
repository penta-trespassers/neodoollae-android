package com.pentatrespassers.neodoollae.view.login.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.databinding.FragmentHomeBinding
import com.pentatrespassers.neodoollae.dto.User

class HomeFragment private constructor() : Fragment() {


    private lateinit var bind: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentHomeBinding.inflate(inflater, container, false)
        with(bind) {
            var a:List<User>? = null
//            roomCardRecyclerHome.adapter = RoomCardAdapter(this@HomeFragment, roomList)
            return root
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }


}