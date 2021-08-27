package com.pentatrespassers.neodoollae.view.login.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.FragmentAroundBinding


class AroundFragment private constructor() : Fragment(), OnMapReadyCallback {

    private lateinit var bind: FragmentAroundBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentAroundBinding.inflate(inflater, container, false)
        with(bind) {
            val fm = childFragmentManager
            val mapFragment = fm.findFragmentById(R.id.naverMap) as MapFragment?
                ?: MapFragment.newInstance().also {
                    fm.beginTransaction().add(R.id.naverMap, it).commit()
                }
            mapFragment.getMapAsync(this@AroundFragment)
            return root
        }

    }

    private var previousPanelHeight = 0

    @UiThread
    override fun onMapReady(naverMap: NaverMap) {
        with (bind) {
            naverMap.setOnMapClickListener { pointF, latLng ->
                if (mapSearchViewAround.visibility == View.VISIBLE) {
                    mapSearchViewAround.visibility = View.GONE
                    previousPanelHeight = slidingUpPanel.panelHeight
                    slidingUpPanel.panelHeight = 0
                } else {
                    mapSearchViewAround.visibility = View.VISIBLE
                    slidingUpPanel.panelHeight = previousPanelHeight
                }
            }

            naverMap.uiSettings.isLocationButtonEnabled = false
            naverMap.uiSettings.isCompassEnabled = false
            location.map = naverMap
            compass.map = naverMap
        }

    }


    companion object {
        fun newInstance() = AroundFragment()
    }
}

