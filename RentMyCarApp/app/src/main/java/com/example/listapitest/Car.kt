package com.example.listapitest

import com.google.android.gms.maps.model.LatLng
import com.google.android.material.textfield.TextInputEditText
import com.google.maps.android.clustering.ClusterItem

data class Car(
    val brand: String,
    val model: String,
    val yearOfBuild: Int,
    val kilometers: Int,
    val weight: Double,
    val category: String,
    val latlng: LatLng,
    val location: LatLng,
    val cost: Double): ClusterItem {
    /**
     * The position of this marker. This must always return the same value.
     */
    override fun getPosition(): LatLng = latlng

    /**
     * The title of this marker.
     */
    override fun getTitle(): String = model

    /**
     * The description of this marker.
     */
    override fun getSnippet(): String = "$model ($yearOfBuild)"
}
