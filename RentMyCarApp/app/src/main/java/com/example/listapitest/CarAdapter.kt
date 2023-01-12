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
import kotlinx.android.synthetic.main.car_list_item.view.*

class CarAdapter(val context: Context) :  RecyclerView.Adapter<CarAdapter.CarViewHolder>() {
    val client by lazy { RentMyCarApiClient.create() }
    var cars: ArrayList<Car> = ArrayList()

    init { getCars() }

    class CarViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CarAdapter.CarViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.car_list_item, parent, false)

        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.view.model.text = cars[position].model
        holder.view.brand.text = cars[position].brand
        holder.view.cost.text = cars[position].cost.toString()
        holder.view.kilometers.text = cars[position].kilometers.toString()
    }

    override fun getItemCount() = cars.size

    fun getCars(kilometers: String = "", cost: String = "") {
        client.getCars(kilometers, cost)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                cars.clear()
                if(result.isNotEmpty()) {
                    cars.addAll(result)
                }
                notifyDataSetChanged()
            },{ error ->
                Toast.makeText(context, "Refresh error: ${error.message}", Toast.LENGTH_LONG).show()
                Log.e("ERRORS", error.message.toString())
                Log.e("ERRORS2", error.toString())

            })
    }
}