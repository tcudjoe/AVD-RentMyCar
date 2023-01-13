package com.example.listapitest

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsFragment : Fragment() {
    private lateinit var mMap: GoogleMap

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        // Avans Hogeschoollaan: 51.5840579881456, 4.797372671775713
        val avansHA = LatLng(51.5840579881456, 4.797372671775713)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(avansHA))
        // camera update with zoom:
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(avansHA, 8f))

        val markerHA = mMap.addMarker(MarkerOptions().position(avansHA).title("Avans HA"))
        markerHA?.tag = 0

        mMap.setOnMarkerClickListener { marker ->
            var clickCount = 1 + marker.tag as Int

            marker.tag = clickCount

//            Toast.makeText(this, "${marker.title} is $clickCount aangeklikt", Toast.LENGTH_SHORT).show()
            true
        }


        mMap.uiSettings.apply {
            isZoomControlsEnabled = true
            isTiltGesturesEnabled = true
        }

        mMap.apply {
            setMinZoomPreference(8f)
            setMaxZoomPreference(20f)
        }

//        mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        val cameraPosition = CameraPosition.builder()
            .target(avansHA)
            .zoom(17f)
            .bearing(90f)
            .tilt(45f)
            .build()

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

        // Add polylines to the map.
        // Polylines are useful to show a route or some other connection between points.
        val rondjeLD = googleMap.addPolyline(
            PolylineOptions()
                .clickable(true)
                .add(
                    LatLng(51.58589756724664, 4.794358257204294),
                    LatLng(51.58621754669707, 4.795420411974192),
                    LatLng(51.58612421959015, 4.796343091875315),
                    LatLng(51.58590423350819, 4.796246532350779),
                    LatLng(51.58562424968158, 4.7959246672689915),
                    LatLng(51.58537092949463, 4.7952487505972385),
                    LatLng(51.585444259167666, 4.794594291597605),
                    LatLng(51.58563091598323, 4.794315341860056),
                    LatLng(51.58589756724664, 4.794358257204294)
                )
        )

        rondjeLD.tag = "rondje LD"

        mMap.setOnPolylineClickListener { polyline ->
            if (polyline.pattern == null) {
                polyline.pattern = listOf<PatternItem>(Gap(20f), Dot())
            } else {
                polyline.pattern = null
            }
        }


//        addMarkers(mMap)
//        addClusterdMarkers()
//
        // camera position depending on Clustered markers:
        // setOnMapLoadedCallback: Sets a callback that's invoked when this map has finished rendering.
//        mMap.setOnMapLoadedCallback {
//            val bounds = LatLngBounds.builder()
//            blindwalls.forEach { blindWall ->
//                bounds.include(blindWall.latLng)
//            }
//            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 50))
//        }
//
//        mMap.setInfoWindowAdapter(BlindWallMarkerAdapter(this))


    }

    // wordt na het maken van clustered items miet meer benut.
//    private fun addMarkers() {
//        blindwalls.forEach { blindWall ->
//            val marker = mMap.addMarker(
//                MarkerOptions()
//                    .title(blindWall.name)
//                    .position(blindWall.latLng)
//            )
//            marker?.tag = blindWall
//        }
//    }
//
//    private fun addClusterdMarkers() {
//        val clusterManager = ClusterManager<>(this, mMap)
//        clusterManager.addItems(blindwalls)
//        clusterManager.cluster()
//
//        mMap.setOnCameraIdleListener {
//            clusterManager.onCameraIdle()
//        }
//    }

}