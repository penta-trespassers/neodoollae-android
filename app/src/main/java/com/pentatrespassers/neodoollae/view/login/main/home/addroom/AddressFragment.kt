package com.pentatrespassers.neodoollae.view.login.main.home.addroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.databinding.FragmentAddressBinding

class AddressFragment constructor() : Fragment() {

    private lateinit var bind: FragmentAddressBinding

    val address
        get() = bind.addressEditText.text.toString()
    val detailAddress
        get() = bind.detailAddressEditText.text.toString()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentAddressBinding.inflate(inflater, container, false)
        with(bind) {
            return root
        }
    }

    companion object {
        fun newInstance() = AddressFragment()
    }
}