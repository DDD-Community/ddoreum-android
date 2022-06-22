package com.project.ddoreum.mountaininfo

import androidx.fragment.app.viewModels
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.project.ddoreum.R
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.databinding.FragmentMountainInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MountainInfoFragment : BaseFragment<FragmentMountainInfoBinding>(R.layout.fragment_mountain_info), OnMapReadyCallback {

    override val viewModel: MountainInfoViewModel by viewModels()

    override fun initLayout() {
        initMapFragment()
        initGetData()
    }

    private fun initGetData() {
        viewModel.getAllMountainInfo()
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

    companion object {
        fun newInstance() = MountainInfoFragment()
    }
}