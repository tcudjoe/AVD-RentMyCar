package com.example.listapitest

import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName

fun toCars(): Car = Car(
	yearOfBuild = yearOfBuild.toInt(),
	model = model,
	cost = cost as Double,
	location = LatLng(latitude as Double, longitude as Double),
	brand = brand,
	category = category,
	kilometers = kilometers,
	weight = weight as Double,
	latlng = latlng,
)

data class CarResponse(

	@field:SerializedName("CarResponse")
	val carResponse: List<CarResponseItem>
)

data class CarResponseItem(

	@field:SerializedName("yearOfBuild")
	val yearOfBuild: Int,

	@field:SerializedName("cost")
	val cost: Any,

	@field:SerializedName("latitude")
	val latitude: Any,

	@field:SerializedName("weight")
	val weight: Any,

	@field:SerializedName("carId")
	val carId: Int,

	@field:SerializedName("numberOfSeats")
	val numberOfSeats: Int,

	@field:SerializedName("rentalService")
	val rentalService: Any,

	@field:SerializedName("kilometers")
	val kilometers: Int,

	@field:SerializedName("model")
	val model: String,

	@field:SerializedName("orders")
	val orders: List<Any>,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("brand")
	val brand: String,

	@field:SerializedName("longitude")
	val longitude: Any
)
