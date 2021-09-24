package com.pentatrespassers.neodoollae.view.login.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.FragmentReservationBinding
import com.pentatrespassers.neodoollae.network.RetrofitClient
import com.pentatrespassers.neodoollae.view.login.main.reservation.ReservationAdapter

class ReservationFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentReservationBinding


    private val reservationAdapter by lazy {
        ReservationAdapter(requireContext(), arrayListOf())
    }

    private fun refreshReservationList() {
        RetrofitClient.getAllMyReservations { _, response ->
            reservationAdapter.reservationList = response.body()!!
            reservationAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentReservationBinding.inflate(inflater, container, false)
        with(bind) {
            reservationRecycler.setHasFixedSize(true)
            reservationRecycler.adapter = reservationAdapter
            reservationRecycler.addItemDecoration(DividerItemDecoration(requireContext(), 1))
            refreshReservationList()

            //implement spinner
            filterSpinner.adapter =
                ArrayAdapter.createFromResource(
                    requireContext(),
                    R.array.spinnerArrayReservation,
                    android.R.layout.simple_spinner_item
                ).apply {
                    // Specify the layout to use when the list of choices appears
                    setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                }

            filterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {}
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    position: Int,
                    p3: Long
                ) {
                    when (position) {
                        0 -> {
                            //전체보기
                        }
                        1 -> {
                            //내 예약만
                        }
                        2 -> {
                            //내 방 예약만
                        }
                    }
                }
            }
            return root
        }
    }

    companion object {
        fun newInstance() = ReservationFragment()
    }
}