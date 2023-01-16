package com.example.listapitest

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.*
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.clustering.ClusterManager



class MapsFragment : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var binding: MapsFragment

    private val cars: List<Car> by lazy {
        MapReader(this).read()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


//        // Avans Hogeschoollaan: 51.5840579881456, 4.797372671775713
//        val avansHA = LatLng(51.5840579881456, 4.797372671775713)
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(avansHA))
//        // camera update with zoom:
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(avansHA, 8f))
//
//        val markerHA = mMap.addMarker(MarkerOptions().position(avansHA).title("Avans HA"))
//        markerHA?.tag = 0
//
//        mMap.setOnMarkerClickListener { marker ->
//            var clickCount = 1 + marker.tag as Int
//
//            marker.tag = clickCount
//
//            Toast.makeText(this, "${marker.title} is $clickCount aangeklikt", Toast.LENGTH_SHORT)
//                .show()
//            true
//        }
//
//        mMap.uiSettings.apply {
//            isZoomControlsEnabled = true
//            isTiltGesturesEnabled = true
//            isRotateGesturesEnabled = true
//            isMyLocationButtonEnabled = true
//        }
//        mMap.apply {
//            setMinZoomPreference(8f)
//            setMaxZoomPreference(20f)
//        }
//
////        mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
//        val cameraPosition = CameraPosition.builder()
//            .target(avansHA)
//            .zoom(17f)
//            .bearing(90f)
//            .tilt(45f)
//            .build()
//
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
//
//        mMap.setOnMarkerClickListener { marker ->
//            val clickCount = ((marker.tag ?: 0) as Int) + 1
//            marker.tag = clickCount
//            Toast.makeText(
//                this,
//                "${marker.title} is $clickCount aangeklikt", Toast.LENGTH_LONG
//            ).show()
//            true //the click-event is handled
//        }

        addClusterdMarkers()

        mMap.setOnMapLoadedCallback {
            val bounds = LatLngBounds.builder()
            cars.forEach { car ->
                bounds.include(car.latlng)
            }
            // todo show effect using this
            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 50))
        }

        mMap.setInfoWindowAdapter(CarAdapter(this))


    }

    private fun addMarkers() {
        cars.forEach { car ->
            val marker = mMap.addMarker(
                MarkerOptions()
                    .position(car.latlng)
            )
            marker?.tag = car
        }
    }

    private fun addClusterdMarkers() {
        val clusterManager = ClusterManager<Car>(this, mMap)
        clusterManager.addItems(cars)
        clusterManager.cluster()

        mMap.setOnCameraIdleListener {
            clusterManager.onCameraIdle()
        }
    }
}

private fun GoogleMap.setInfoWindowAdapter(carAdapter: CarAdapter) {

}
