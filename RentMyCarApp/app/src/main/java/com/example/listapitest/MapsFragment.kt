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
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.maps.android.clustering.ClusterManager


class MapsFragment : FragmentActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

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

            Toast.makeText(this, "${marker.title} is $clickCount aangeklikt", Toast.LENGTH_SHORT).show()
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
        addClusterdMarkers()
//
        // camera position depending on Clustered markers:
        // setOnMapLoadedCallback: Sets a callback that's invoked when this map has finished rendering.
        mMap.setOnMapLoadedCallback {
            val bounds = LatLngBounds.builder()
            cars.forEach { car ->
                bounds.include(car.latLng)
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 50))
        }

        mMap.setInfoWindowAdapter(CarAdapter(this))


    }

    // wordt na het maken van clustered items miet meer benut.
    private fun addMarkers() {
        cars.forEach { cars ->
            val marker = mMap.addMarker(
                MarkerOptions()
                    .title(cars.model)
                    .position(cars.latLng)
            )
            marker?.tag = cars
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

//    private val permissionCode = 101
//    var mGoogleMap: GoogleMap? = null
//    var coordinates = MutableLiveData<List<Car>>()
//    private lateinit var currentLocation: Location
////    private val viewModel: CarAdapter by CarAdapter.CarViewHolder()
//    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
//    private val viewModel: MapsFragment by lazy {viewModels(MapsFragment::class)}
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        fusedLocationProviderClient =  LocationServices.getFusedLocationProviderClient(this@MapsFragment)
//        fetchLocation()
//
//        viewModel.coordinates.observe(this, Observer {
//            // Clear previous markers:
//            mGoogleMap?.clear()
//
//            // Place all current markers:
//                it.forEach { car ->
//                val latLng = LatLng(car.latitude, car.longitude)
//                val markerOptions = MarkerOptions()
//                markerOptions.position(latLng)
//                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
//
//                val marker = mGoogleMap?.addMarker(markerOptions)
//            }
//        })
//    }
//
//    private fun fetchLocation() {
//        if (ActivityCompat.checkSelfPermission(
//                this, Manifest.permission.ACCESS_FINE_LOCATION) !=
//            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
//            PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this,
//                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), permissionCode)
//            return
//        }
//
//        val task = fusedLocationProviderClient.lastLocation
//        task.addOnSuccessListener {
//            location ->; if (location != null) {
//                currentLocation = location
//                Toast.makeText(applicationContext, currentLocation.latitude.toString() + "" +
//                        currentLocation.longitude, Toast.LENGTH_SHORT).show()
//                val supportMapFragment = (supportFragmentManager.findFragmentById(R.id.map) as
//                        SupportMapFragment?)!!
//                supportMapFragment.getMapAsync(this@MapsFragment)
//            }
//        }
//    }
//
//    override fun onMapReady(mMap: GoogleMap) {
//        val latLng = LatLng(currentLocation.latitude, currentLocation.longitude)
//            val markerOptions = MarkerOptions().position(latLng).title("Current location")
//        mMap?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
//        mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5f))
//        mMap?.addMarker(markerOptions)
//    }
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>,
//                                            grantResults: IntArray) {
//            when (requestCode) {
//                permissionCode -> if (grantResults.isNotEmpty() && grantResults[0] ==
//                PackageManager.PERMISSION_GRANTED) {
//                fetchLocation()
//            }
//        }
//    }
}

private fun Any.setInfoWindowAdapter(carAdapter: CarAdapter) {

}
