package com.pentatrespassers.neodoollae.view.login.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.util.FusedLocationSource
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.FragmentAroundBinding
import com.pentatrespassers.neodoollae.dto.Room
import com.pentatrespassers.neodoollae.view.login.main.around.MapListRecyclerViewAdapter
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.updateLayoutParams
import com.bumptech.glide.Glide
import com.google.android.gms.location.FusedLocationProviderClient
import com.naver.maps.map.util.MarkerIcons
import com.pentatrespassers.neodoollae.dto.Room.Companion.STATUS_CLOSED
import com.pentatrespassers.neodoollae.dto.Room.Companion.STATUS_OPEN
import com.pentatrespassers.neodoollae.dto.Room.Companion.STATUS_RESTRICTED
import com.pentatrespassers.neodoollae.lib.Util.show
import com.pentatrespassers.neodoollae.lib.Util.gone
import com.pentatrespassers.neodoollae.lib.Util.hide
import com.pentatrespassers.neodoollae.view.login.main.home.RoomProfileActivity
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import splitties.bundle.putExtras
import splitties.fragments.start


class AroundFragment private constructor() : Fragment(), OnMapReadyCallback {

    private lateinit var bind: FragmentAroundBinding
    private lateinit var mapListAdapter: MapListRecyclerViewAdapter

    private val PERMISSION_REQUEST_CODE = 1000
    private val PRIMARY_PANEL_HEIGHT = 744

    private lateinit var mLocationSource: FusedLocationSource
    private lateinit var mNaverMap: NaverMap
    private var previousPanelHeight = PRIMARY_PANEL_HEIGHT

    private var mapList = makeDummyData()
    private var markerList = mutableListOf<Marker>()
    private var infoWindowList = mutableListOf<InfoWindow>()

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var myLatitude : Double? = null
    private var myLongitude : Double? = null


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

            // getMapAsync를 호출하여 비동기로 onMapReady 콜백 메서드 호출
            // onMapReady에서 NaverMap 객체를 받음
            mapFragment.getMapAsync(this@AroundFragment)
            mapFragment.getMapAsync {
                location.map = mNaverMap
                compass.map = mNaverMap
            }
            // 위치를 반환하는 구현체인 FusedLocationSource 생성
            mLocationSource = FusedLocationSource(this@AroundFragment, PERMISSION_REQUEST_CODE)

            mapListAdapter = MapListRecyclerViewAdapter(
                requireContext(),
                makeDummyData(),
                37.5670135,
                126.9783740
            )
            mapListRecyclerViewAround.adapter = mapListAdapter

            //서치바 구현
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


    @UiThread
    override fun onMapReady(naverMap: NaverMap) {
        with(bind) {
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

            // Set Markers
            for (room: Room in mapList) {
                markerList.add(
                    Marker().apply {
                        position = LatLng(room.latitude, room.longitude)
                        icon = MarkerIcons.GRAY
                        iconTintColor = Color.BLUE
                        map = naverMap
                        onClickListener = markerListener(room)
                    }
                )
                infoWindowList.add(
                    InfoWindow().apply {
                        adapter = object : InfoWindow.DefaultTextAdapter(requireContext()) {
                            override fun getText(infoWindow: InfoWindow): CharSequence {
                                return room.roomName
                            }
                        }
                        alpha = 0.7F
                    }
                )
            }

            for (marker: Marker in markerList) {
                marker.map = naverMap
                infoWindowList.run {
                    get(markerList.indexOf(marker)).open(marker)
                }
            }

            // NaverMap 객체 받아서 NaverMap 객체에 위치 소스 지정
            mNaverMap = naverMap
            naverMap.locationSource = mLocationSource

            //네이버 위치추적모드
            naverMap.locationTrackingMode = LocationTrackingMode.NoFollow

            // 권한확인. 결과는 onRequestPermissionsResult 콜백 매서드 호출
            //  ActivityCompat.requestPermissions(this@AroundFragment, PERMISSIONS, PERMISSION_REQUEST_CODE)


        }

    }

    //더미데이터 만든 함수 하나 선언
    private fun makeDummyData(): List<Room> {
        return arrayListOf(
            Room(
                1, 1, "써니", "sunnyRoom", "한양대학교 어딘가", null, "000호",
                "샘플 데이터입니다", 37.5670135, 126.9783740, STATUS_RESTRICTED
            ),
            Room(
                2, 2, "서진", "seojinRoom", "한양대학교 어딘가", null, "000호",
                "샘플 데이터입니다", 37.5680136, 126.9783740, STATUS_CLOSED
            ),
            Room(
                4, 25, "황진하", "진하꺼", "한양대학교 어딘가", null, "000호",
                "샘플 데이터입니다", 37.5670135, 126.9793743, STATUS_OPEN
            )
        )
    }

    companion object {
        fun newInstance() = AroundFragment()
    }

    inner class PanelEventListener : SlidingUpPanelLayout.PanelSlideListener {
        // 패널이 슬라이드 중일 때
        override fun onPanelSlide(panel: View?, slideOffset: Float) {

        }

        // 패널의 상태가 변했을 때
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
            if (!mLocationSource!!.isActivated) { // 권한 거부됨
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
                // 현재 마커에 정보 창이 열려있지 않을 경우 엶
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

            //sliding layout 내리기
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

