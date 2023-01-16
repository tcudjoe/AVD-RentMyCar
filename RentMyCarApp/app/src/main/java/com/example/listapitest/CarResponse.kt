package com.example.listapitest

import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName

// generated with

fun CarResponse.toCars(): Car = Car(
	carId = id,
	brand = brand,
	model = model,
	yearOfBuild = yearOfBuild,
	longitude = longitude,
	latitude = latitude,
	latLng = LatLng(latitude,longitude)
)

data class CarResponse(
	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("brand")
	val brand: String,

	@field:SerializedName("latlng")
	val latLng: LatLng,

	@field:SerializedName("model")
	val model: String,

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("yearOfBuild")
	val yearOfBuild: Int,

	@field:SerializedName("latitude")
	val latitude: Double,

	@field:SerializedName("longitude")
	val longitude: Double
)