package com.pentatrespassers.neodoollae.view.login.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.annotation.UiThread
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.gms.location.FusedLocationProviderClient
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.FragmentAroundBinding
import com.pentatrespassers.neodoollae.dto.Room
import com.pentatrespassers.neodoollae.lib.Util
import com.pentatrespassers.neodoollae.lib.Util.gone
import com.pentatrespassers.neodoollae.lib.Util.hide
import com.pentatrespassers.neodoollae.lib.Util.show
import com.pentatrespassers.neodoollae.network.RetrofitClient
import com.pentatrespassers.neodoollae.view.login.main.around.MapListAdapter
import com.pentatrespassers.neodoollae.view.login.main.home.RoomProfileActivity
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import splitties.bundle.putExtras
import splitties.fragments.start


class AroundFragment constructor() : Fragment(), OnMapReadyCallback {

    private lateinit var bind: FragmentAroundBinding


    private val PERMISSION_REQUEST_CODE = 1000
    private val PRIMARY_PANEL_HEIGHT = 744

    private lateinit var mLocationSource: FusedLocationSource
    private lateinit var mNaverMap: NaverMap
    private var previousPanelHeight = PRIMARY_PANEL_HEIGHT

    private var markerList = mutableListOf<Marker>()
    private var infoWindowList = mutableListOf<InfoWindow>()

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val mapListAdapter by lazy {
        MapListAdapter(requireContext())
    }

    var naverMap: NaverMap? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentAroundBinding.inflate(inflater, container, false)
        with(bind) {
            slidingUpPanel.addPanelSlideListener(this@AroundFragment.PanelEventListener())
            slidingUpPanel.panelHeight = PRIMARY_PANEL_HEIGHT
            singleRoomInfoConstraintLayout.gone()

            // set mapFragment
            val fm = childFragmentManager
            val mapFragment = fm.findFragmentById(R.id.naverMap) as MapFragment?
                ?: MapFragment.newInstance().also {
                    fm.beginTransaction().add(R.id.naverMap, it).commit()
                }

            // getMapAsync??? ???????????? ???????????? onMapReady ?????? ????????? ??????
            // onMapReady?????? NaverMap ????????? ??????
            mapFragment.getMapAsync(this@AroundFragment)
            mapFragment.getMapAsync {
                location.map = mNaverMap
                compass.map = mNaverMap
            }
            // ????????? ???????????? ???????????? FusedLocationSource ??????
            mLocationSource = FusedLocationSource(this@AroundFragment, PERMISSION_REQUEST_CODE)

            mapListRecyclerViewAround.adapter = mapListAdapter

            //????????? ??????
            mapSearchViewAround.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    mapListAdapter.filter!!.filter(query)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    mapListAdapter.filter!!.filter(newText)
                    return false
                }
            }
            )
            return root
        }

    }

    private fun refresh() {
        RetrofitClient.getRooms { _, response ->
            markerList.clear()
            infoWindowList.clear()

            response.body()?.let {
                mapListAdapter.refresh(it)
                it.forEach { room ->
                    val marker = Marker().apply {
                        position = LatLng(room.latitude, room.longitude)
                        icon = MarkerIcons.GRAY
                        iconTintColor = Color.BLUE
                        map = naverMap
                        onClickListener = markerListener(room)
                    }
                    markerList.add(marker)
                    infoWindowList.add(
                        InfoWindow().apply {
                            adapter = object : InfoWindow.DefaultTextAdapter(requireContext()) {
                                override fun getText(infoWindow: InfoWindow): CharSequence {
                                    return room.roomName
                                }
                            }
                            alpha = 0.7F
                            open(marker)
                        }
                    )
                }
            }

        }

    }


    @UiThread
    override fun onMapReady(naverMap: NaverMap) {
        with(bind) {
            this@AroundFragment.naverMap = naverMap
            val uiSettings = naverMap.uiSettings
            uiSettings.isLocationButtonEnabled = false
            uiSettings.isCompassEnabled = false

            naverMap.setOnMapClickListener { _, _ ->
                infoWindowListTransparent(infoWindowList)
                when (mapSearchViewAround.visibility) {
                    View.VISIBLE -> {
                        when (slidingUpPanel.panelHeight) {
                            0 -> {
                                mapSearchViewAround.hide()
                            }
                            else -> {
                                previousPanelHeight = slidingUpPanel.panelHeight
                                slidingUpPanel.panelHeight = 0
                            }
                        }
                    }
                    else -> {
                        when (singleRoomInfoConstraintLayout.visibility) {
                            View.VISIBLE -> singleRoomInfoConstraintLayout.gone()
                            else -> {
                                mapSearchViewAround.show()
                                slidingUpPanel.panelHeight = PRIMARY_PANEL_HEIGHT
                            }
                        }
                    }
                }
            }
            refresh()

            for (marker: Marker in markerList) {
                marker.map = naverMap
                infoWindowList.run {
                    get(markerList.indexOf(marker)).open(marker)
                }
            }

            // NaverMap ?????? ????????? NaverMap ????????? ?????? ?????? ??????
            mNaverMap = naverMap
            naverMap.locationSource = mLocationSource

            //????????? ??????????????????
            naverMap.locationTrackingMode = LocationTrackingMode.NoFollow

            Util.getLocation(requireContext())?.let {
                naverMap.moveCamera(CameraUpdate.scrollTo(LatLng(it)))
            }


            // ????????????. ????????? onRequestPermissionsResult ?????? ????????? ??????
            //  ActivityCompat.requestPermissions(this@AroundFragment, PERMISSIONS, PERMISSION_REQUEST_CODE)


        }

    }

    companion object {
        fun newInstance() = AroundFragment()
    }

    inner class PanelEventListener : SlidingUpPanelLayout.PanelSlideListener {
        // ????????? ???????????? ?????? ???
        override fun onPanelSlide(panel: View?, slideOffset: Float) {

        }

        // ????????? ????????? ????????? ???
        override fun onPanelStateChanged(
            panel: View?,
            previousState: SlidingUpPanelLayout.PanelState?,
            newState: SlidingUpPanelLayout.PanelState?
        ) {
            if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED) {

            } else if (newState == SlidingUpPanelLayout.PanelState.EXPANDED) {

            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (mLocationSource!!.onRequestPermissionsResult(
                requestCode, permissions,
                grantResults
            )
        ) {
            if (!mLocationSource!!.isActivated) { // ?????? ?????????
                mNaverMap!!.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun markerListener(room: Room) = Overlay.OnClickListener { overlay ->
        with(bind) {
            infoWindowListTransparent(infoWindowList)

            val marker = overlay as Marker

            if (marker.infoWindow == null) {
                // ?????? ????????? ?????? ?????? ???????????? ?????? ?????? ???
                infoWindowList.run {
                    get(markerList.indexOf(marker)).open(marker)
                }
            } else {
                marker.infoWindow?.alpha = 1F
                marker.infoWindow?.zIndex = 0
            }

            // Show Room Info Constraint
            singleRoomInfoConstraintLayout.show()
            when (room.roomImages.isNullOrEmpty()) {
                true -> {
                    singleRoomImageView.apply {
                        background = AppCompatResources.getDrawable(
                            context,
                            R.drawable.ic_common_bed
                        )
                        updateLayoutParams {
                            width = 96
                            height = 96
                        }
                    }
                }
                false -> {
                    singleRoomImageView.apply {
                        updateLayoutParams {
                            width = 0
                            height = 0
                        }
                    }
                    Glide.with(requireContext())
                        .load(room.roomImages?.get(0))
                        .into(singleRoomImageView)
                }
            }
            singleRoomNameTextView.text = room.roomName
            singleRoomHostNameTextView.text = room.nickname
            singleRoomAddressTextView.text = room.address
            singleRoomDetailAddressTextView.text = room.detailAddress
            singleRoomInfoConstraintLayout.setOnClickListener {
                start<RoomProfileActivity> {
                    putExtras(RoomProfileActivity.Extras) {
                        this.room = room
                    }
                }
            }

            //sliding layout ?????????
            mapSearchViewAround.hide()
            previousPanelHeight = slidingUpPanel.panelHeight
            slidingUpPanel.panelHeight = 0

            true
        }
    }

    private fun infoWindowListTransparent(infoWindowList: List<InfoWindow>) {
        for (infoWindow in infoWindowList) {
            infoWindow.alpha = 0.7F
        }
    }
}

