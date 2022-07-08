package com.project.ddoreum.mountaininfo.map

import android.content.Intent
import android.util.Log
import androidx.fragment.app.viewModels
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.LocationOverlay
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.overlay.OverlayImage
import com.project.ddoreum.R
import com.project.ddoreum.common.repeatCallDefaultOnResume
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.databinding.FragmentMountainInfoMapBinding
import com.project.ddoreum.domain.entity.mountain.MountainInfoData
import com.project.ddoreum.mountaininfo.MountainInfoViewModel
import com.project.ddoreum.mountaininfo.detail.MountainInfoDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async

@AndroidEntryPoint
class MountainInfoMapFragment :
    BaseFragment<FragmentMountainInfoMapBinding>(R.layout.fragment_mountain_info_map),
    OnMapReadyCallback {

    private lateinit var marker: Marker
    private lateinit var map: NaverMap
    private lateinit var locationOverlay: LocationOverlay
    private var _prevMarker = Pair<Marker?, MountainInfoData?>(null, null)
    private var markerList = arrayListOf<Marker>()

    companion object {
        val TAG = this::class.java.toString()
        fun newInstance() = MountainInfoMapFragment()
    }

    override val viewModel: MountainInfoMapViewModel by viewModels()

    private val sharedViewModel: MountainInfoViewModel by viewModels({ requireParentFragment() })

    override fun initLayout() {
        bind {
            viewmodel = viewModel
            lifecycleOwner = this@MountainInfoMapFragment
        }
        initMapFragment()
    }

    private fun collectFlow() = viewLifecycleOwner.repeatCallDefaultOnResume {
        sharedViewModel.mountainList.collect {
            clearAllMarker()
            setMountainPinMarker(it)
        }
    }

    private fun setMountainPinMarker(data: ArrayList<MountainInfoData>) = with(binding) {
        data.forEach {
            setMarker(it)
        }
    }

    private fun initMapFragment() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as MapFragment?
            ?: MapFragment.newInstance().also {
                childFragmentManager.beginTransaction().add(R.id.mapView, it).commit()
            }
        mapFragment?.getMapAsync(this)
        binding.cvMountainInfo.setOnClickListener {
            val currentMountainInfo = viewModel.clickedMountainData.value
            currentMountainInfo?.let {
                val intent = Intent(activity, MountainInfoDetailActivity::class.java).apply {
                    putExtra(MountainInfoDetailActivity.MOUNTAIN_NAME, it.name)
                }
                startActivity(intent)
            }
        }
    }

    override fun onMapReady(naverMap: NaverMap) {
        map = naverMap
        locationOverlay = map.locationOverlay
        val defaultPosition = LatLng(37.52732472751029, 126.9804849269383)
        map.moveCamera(CameraUpdate.scrollAndZoomTo(defaultPosition, 6.0))
        collectFlow()
        mapOnClick()
    }

    private fun mapOnClick() {
        map.setOnMapClickListener { _, _ ->
            viewModel.updateMountainCardViewState(false)
            refreshPrevMarker()
        }
    }

    private operator fun Overlay.OnClickListener?.invoke(
        mapsFragment: MountainInfoMapFragment,
        markerPosition: LatLng,
        data: MountainInfoData
    ) {
        mapsFragment.marker.setOnClickListener {
            it.map = null
            refreshPrevMarker()
            clickedMarker(markerPosition.latitude, markerPosition.longitude, data)
            viewModel.updateClickedMountainData(data.mountainInfo)
            viewModel.updateMountainCardViewState(true)
            map.moveCamera(
                CameraUpdate.scrollTo(
                    LatLng(
                        markerPosition.latitude,
                        markerPosition.longitude
                    )
                )
            )
            return@setOnClickListener true
        }
    }

    private fun refreshPrevMarker() {
        if (_prevMarker.first != null) {
            val marker = _prevMarker.first
            val data = _prevMarker.second
            marker?.let { it.map = null }
            data?.let { setMarker(it) }
            _prevMarker = null to null
        }
    }

    private fun setMarker(it: MountainInfoData) {
        marker = Marker()
        marker.let { marker ->
            val latitude = checkNotNull(it.latitude)
            val longitude = checkNotNull(it.longitude)
            marker.position = LatLng(latitude, longitude)
            marker.icon = OverlayImage.fromResource(R.drawable.ic_pin)
            marker.onClickListener(this@MountainInfoMapFragment, marker.position, it)
            marker.map = map
        }
        markerList.add(marker)
    }

    private fun clickedMarker(latitude: Double, longitude: Double, data: MountainInfoData) {
        marker = Marker()
        marker.let {
            it.position = LatLng(latitude, longitude)
            it.icon = OverlayImage.fromResource(R.drawable.ic_pin_selected)
            it.map = map
        }
        _prevMarker = marker to data
    }

    private fun clearAllMarker() {
        if (!markerList.isNullOrEmpty()) {
            markerList.forEach {
                it.map = null
            }
        }
    }
}