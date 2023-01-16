package com.example.listapitest

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.list_item.view.*

class CarAdapter(val context: Context) :  RecyclerView.Adapter<CarAdapter.CarViewHolder>() {
    val client by lazy { RentMyCarApiClient.create() }
    var cars: ArrayList<Car> = ArrayList()

    init { refreshCars() }

    class CarViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CarAdapter.CarViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.view.model.text = cars[position].model
        holder.view.brand.text = cars[position].brand
//        holder.view.whereIsTheCar.text = cars[position].whereIsTheCar
    }

    override fun getItemCount() = cars.size

    fun refreshCars() {
        client.getCars()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                cars.clear()
                cars.addAll(result)
                notifyDataSetChanged()
            },{ error ->
                Toast.makeText(context, "Refresh error: ${error.message}", Toast.LENGTH_LONG).show()
                Log.e("ERRORS", error.message.toString())
            })
    }
}