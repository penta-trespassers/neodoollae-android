package com.pentatrespassers.neodoollae.view.login.main

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.pentatrespassers.neodoollae.common.adapter.RoomCardAdapter
import com.pentatrespassers.neodoollae.databinding.FragmentAroundBinding
import com.pentatrespassers.neodoollae.dto.Room
import com.pentatrespassers.neodoollae.view.login.main.around.MapListRecyclerViewAdapter
import android.widget.Toast
import com.naver.maps.map.util.MarkerIcons


class AroundFragment private constructor() : Fragment(), OnMapReadyCallback {

    private lateinit var bind: FragmentAroundBinding
    private val mapListAdapter by lazy {
        MapListRecyclerViewAdapter(
            requireContext(),
            makeDummyData()
        )
    }


    private val TAG = "MainActivity"

    private val PERMISSION_REQUEST_CODE = 100

    private var mLocationSource: FusedLocationSource? = null
    private var mNaverMap: NaverMap? = null



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

            mapListRecyclerViewAround.adapter = mapListAdapter


            // getMapAsync를 호출하여 비동기로 onMapReady 콜백 메서드 호출
            // onMapReady에서 NaverMap 객체를 받음
            mapFragment.getMapAsync(this@AroundFragment)

            // 위치를 반환하는 구현체인 FusedLocationSource 생성

            mLocationSource = FusedLocationSource(this@AroundFragment, PERMISSION_REQUEST_CODE)




            return root
        }

    }

    private var previousPanelHeight = 0

    @UiThread
    override fun onMapReady(naverMap: NaverMap) {
        with(bind) {
            naverMap.setOnMapClickListener { pointF, latLng ->
                if (mapSearchViewAround.visibility == View.VISIBLE) {
                    mapSearchViewAround.visibility = View.GONE
                    previousPanelHeight = slidingUpPanel.panelHeight
                    slidingUpPanel.panelHeight = 0
                } else {
                    mapSearchViewAround.visibility = View.VISIBLE
                    slidingUpPanel.panelHeight = previousPanelHeight
                    singleRoomInfoConstraintLayout.visibility = View.INVISIBLE
                }

            }

            naverMap.uiSettings.isLocationButtonEnabled = false
            naverMap.uiSettings.isCompassEnabled = false
            location.map = naverMap
            compass.map = naverMap


            Log.d(TAG, "onMapReady")

            // 지도상에 마커 표시
            val mapList = makeDummyData()

            var markerList = mutableListOf<Marker>()
            context?.let { it1 ->
                mapList.forEach {
                    val marker = Marker()
                    marker.position = LatLng(it.latitude, it.longitude)
                    //이미지 설정
                    // marker.setIcon(OverlayImage.fromResource(R.drawable.ic_done_24dp));
                    marker.icon = MarkerIcons.GRAY
                    marker.iconTintColor = Color.BLUE
                    marker.map = naverMap
                    markerList.add(marker)
                    val infoWindow = InfoWindow()

                    infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(it1) {
                        override fun getText(infoWindow: InfoWindow): CharSequence {
                            return it.roomName
                        }
                    }
                    infoWindow.alpha = 0.7f
                    infoWindow.open(marker)

                    // 마커를 클릭하면:
                    val listener = Overlay.OnClickListener { overlay ->
                        val marker = overlay as Marker

                        if (marker.infoWindow == null) {
                            // 현재 마커에 정보 창이 열려있지 않을 경우 엶
                            infoWindow.open(marker)
                        } else {
                            // 이미 현재 마커에 정보 창이 열려있을 경우 닫음
                            infoWindow.close()
                        }
                        //sliding layout 내리기
                        mapSearchViewAround.visibility = View.GONE
                        previousPanelHeight = slidingUpPanel.panelHeight
                        slidingUpPanel.panelHeight = 0


                        singleRoomInfoConstraintLayout.visibility = View.VISIBLE

                        true
                    }
                    marker.onClickListener = listener
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
    fun makeDummyData(): List<Room> {
        val data: List<Room> = arrayListOf(
            Room(
                1, 1, "써니", "sunnyRoom", "한양대학교 어딘가", "000호",
                "샘플 데이터입니다", 37.5670135, 126.9783740
            ),
            Room(
                2, 2, "서진", "seojinRoom", "한양대학교 어딘가", "000호",
                "샘플 데이터입니다", 37.5680136, 126.9783740
            ),
            Room(
                3, 3, "진하", "recasterRoom", "한양대학교 어딘가", "000호",
                "샘플 데이터입니다", 37.5670135, 126.9793743
            )
        )


        return data
    }

    companion object {
        fun newInstance() = AroundFragment()
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

//    override fun onClick(p0: Overlay): Boolean {
//        if (overlay is Marker) {
//            val marker = overlay as Marker
//            if (marker.infoWindow != null) {
//                mInfoWindow.close()
//                Toast.makeText(this.getApplicationContext(), "InfoWindow Close.", Toast.LENGTH_LONG)
//                    .show()
//            } else {
//                mInfoWindow.open(marker)
//                Toast.makeText(this.getApplicationContext(), "InfoWindow Open.", Toast.LENGTH_LONG)
//                    .show()
//            }
//            return true
//        }
//        else{}
//
//        return false
//    }


}

