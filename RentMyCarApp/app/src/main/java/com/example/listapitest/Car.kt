package com.example.listapitest

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class Car( val carId: Int,
                val model: String,
                val brand: String,
                val yearOfBuild: Int,
                val longitude: Double,
                val latitude: Double,
                val latLng: LatLng): ClusterItem {
    /**
     * The position of this marker. This must always return the same value.
     */
    override fun getPosition(): LatLng = latLng

    /**
     * The title of this marker.
     */
    override fun getTitle(): String = model

    /**
     * The description of this marker.
     */
    override fun getSnippet(): String = "$model $brand ($yearOfBuild)"
}