package com.pentatrespassers.neodoollae.view.login.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.FragmentReservationBinding
import com.pentatrespassers.neodoollae.lib.Util.fragmentTransaction
import com.pentatrespassers.neodoollae.network.RetrofitClient
import com.pentatrespassers.neodoollae.view.login.main.friend.FriendListFragment
import com.pentatrespassers.neodoollae.view.login.main.friend.FriendRequestFragment
import com.pentatrespassers.neodoollae.view.login.main.reservation.ReservationAdapter
import com.pentatrespassers.neodoollae.view.login.main.reservation.myReservationFragment
import com.pentatrespassers.neodoollae.view.login.main.reservation.myRoomReservationFragment
import com.pentatrespassers.neodoollae.view.login.main.reservation.waitingReservationFragment

class ReservationFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentReservationBinding

    private val myReservationFragment = myReservationFragment.newInstance()
    private val myRoomReservationFragment = myRoomReservationFragment.newInstance()
    private val waitingReservationFragment = waitingReservationFragment.newInstance()



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



            fragmentTransaction {
                add(R.id.reservationLayout, myReservationFragment)
                add(R.id.reservationLayout, myRoomReservationFragment)
                add(R.id.reservationLayout, waitingReservationFragment)
                hide(myRoomReservationFragment)
                hide(waitingReservationFragment)
            }
            myReservationConstraint.setOnClickListener {
                fragmentTransaction {
                    hide(myRoomReservationFragment)
                    hide(waitingReservationFragment)
                    show(myReservationFragment)
                    myReservationText.setTextColor(ContextCompat.getColor(requireContext(), R.color.trespassBlue_900))
                    myReservationUnderlineConstraint.visibility = View.VISIBLE
                    myRoomReservationText.setTextColor(ContextCompat.getColor(requireContext(), R.color.trespassGray_900))
                    myRoomReservationUnderlineConstraint.visibility = View.GONE
                    waitingRerservationText.setTextColor(ContextCompat.getColor(requireContext(), R.color.trespassGray_900))
                    waitingReservationUnderlineConstraint.visibility = View.GONE
                }
            }
            myRoomReservationConstraint.setOnClickListener {
                fragmentTransaction {
                    hide(myReservationFragment)
                    hide(waitingReservationFragment)
                    show(myRoomReservationFragment)

                    myRoomReservationText.setTextColor(ContextCompat.getColor(requireContext(), R.color.trespassBlue_900))
                    myRoomReservationUnderlineConstraint.visibility = View.VISIBLE
                    myReservationText.setTextColor(ContextCompat.getColor(requireContext(), R.color.trespassGray_900))
                    myReservationUnderlineConstraint.visibility = View.GONE
                    waitingRerservationText.setTextColor(ContextCompat.getColor(requireContext(), R.color.trespassGray_900))
                    waitingReservationUnderlineConstraint.visibility = View.GONE
                }
            }

            waitingReservationConstraint.setOnClickListener {
                fragmentTransaction {
                    hide(myReservationFragment)
                    hide(myRoomReservationFragment)
                    show(waitingReservationFragment)

                    waitingRerservationText.setTextColor(ContextCompat.getColor(requireContext(), R.color.trespassBlue_900))
                    waitingReservationUnderlineConstraint.visibility = View.VISIBLE
                    myReservationText.setTextColor(ContextCompat.getColor(requireContext(), R.color.trespassGray_900))
                    myReservationUnderlineConstraint.visibility = View.GONE
                    myRoomReservationText.setTextColor(ContextCompat.getColor(requireContext(), R.color.trespassGray_900))
                    myRoomReservationUnderlineConstraint.visibility = View.GONE
                }
            }

            return root
        }
    }

    companion object {
        fun newInstance() = ReservationFragment()
    }
}