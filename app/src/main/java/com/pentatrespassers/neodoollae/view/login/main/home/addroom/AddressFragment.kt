package com.pentatrespassers.neodoollae.view.login.main.home.addroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.databinding.FragmentAddressBinding
import com.pentatrespassers.neodoollae.lib.Util.gone
import com.pentatrespassers.neodoollae.lib.Util.show
import com.pentatrespassers.neodoollae.network.RetrofitClient
import com.pentatrespassers.neodoollae.view.login.main.home.addroom.address.SelectAddressAdapter

class AddressFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentAddressBinding

    val address
        get() = bind.addressEditText.text.toString()
    val detailAddress
        get() = bind.detailAddressEditText.text.toString()

    lateinit var selectAddressAdapter: SelectAddressAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentAddressBinding.inflate(inflater, container, false)
        with(bind) {
            selectAddressAdapter = SelectAddressAdapter(requireContext(), this)
            selectAddressRecycler.adapter = selectAddressAdapter
            searchButton.setOnClickListener {
                address.ifBlank { return@setOnClickListener }
                RetrofitClient.searchPlace(address) { _, response ->
                    selectAddressAdapter.documentList = response.body()!!.documents
                    selectAddressAdapter.notifyDataSetChanged()
                    selectAddressConstraint.show()
                    detailAddressEditText.gone()
                }
            }
            return root

        }
    }

    companion object {
        fun newInstance() = AddressFragment()
    }
}