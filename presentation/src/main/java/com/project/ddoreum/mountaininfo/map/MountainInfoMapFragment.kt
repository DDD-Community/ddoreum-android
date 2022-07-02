package com.project.ddoreum.mountaininfo.map

import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.project.ddoreum.R
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.databinding.FragmentMountainInfoMapBinding


class MountainInfoMapFragment : BaseFragment<FragmentMountainInfoMapBinding>(R.layout.fragment_mountain_info_map), OnMapReadyCallback {

    companion object {
        val TAG = this::class.java.toString()
        fun newInstance() = MountainInfoMapFragment()
    }

    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

    override fun initLayout() {
        initMapFragment()
    }

    private fun initMapFragment() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as MapFragment?
            ?: MapFragment.newInstance().also {
                childFragmentManager.beginTransaction().add(R.id.mapView, it).commit()
            }
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(naverMap: NaverMap) {
        val defaultPosition = LatLng(37.52732472751029, 126.9804849269383)
        naverMap.moveCamera(CameraUpdate.scrollAndZoomTo(defaultPosition, 12.0))
    }
}