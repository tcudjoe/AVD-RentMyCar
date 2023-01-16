package com.example.listapitest

import android.content.Context
import java.io.InputStream
import java.io.InputStreamReader

// needs dependency:     implementation 'com.google.code.gson:gson:2.8.6'
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MapReader (private val context: Context) {
    // GSON object responsible for converting from JSON to a BlindWall object
    private val gson = Gson()

    // InputStream representing places.json
    private val inputStream: InputStream
        get() = context.resources.openRawResource(R.raw.car)

    /**
     * Reads the list of place JSON objects in the file places.json
     * and returns a list of Place objects
     */
    fun read(): List<Car> {
        val itemType = object : TypeToken<List<CarResponse>>() {}.type
        val reader = InputStreamReader(inputStream)
        return gson.fromJson<List<CarResponse>>(reader, itemType).map {
            it.toCars()
        }
    }
}
