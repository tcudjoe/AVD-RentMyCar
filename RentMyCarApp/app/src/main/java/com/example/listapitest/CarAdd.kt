package com.example.listapitest

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.listapitest.databinding.CarAddBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.car_add.*

class CarAdd : Fragment() {

    private var _binding: CarAddBinding? = null
    lateinit var adapter:CarAdapter
    private var baseContext:Context? = null
    private val binding get() = _binding!!
    val client by lazy { RentMyCarApiClient.create() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = CarAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseContext = view.context
        adapter = CarAdapter(view.context)

        binding.addCar.setOnClickListener {
            var car = Car(
                input_brand.text.toString(),
                input_model.text.toString(),
                input_year_of_build.text.toString().toIntOrNull()?: 1990,
                input_kilometers.text.toString().toIntOrNull()?: 150000,
                input_weight.text.toString().toDoubleOrNull() ?: 1000.0,
                input_category.text.toString(),
                input_location.to,
                input_cost.text.toString().toDoubleOrNull() ?: 200.00)

            client.addCar(car)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    findNavController().navigate(R.id.car_add_to_car_service_overview)

                },{ error ->
                    Toast.makeText(context, "Refresh error: ${error.message}", Toast.LENGTH_LONG).show()
                    Log.e("ERRORS", error.message.toString())

                })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.refresh -> {
            adapter.getCars()
            Toast.makeText(this.baseContext, "Refreshed", Toast.LENGTH_LONG).show()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}