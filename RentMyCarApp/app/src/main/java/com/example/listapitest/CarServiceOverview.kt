package com.example.listapitest

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listapitest.databinding.CarServiceOverviewBinding
import kotlinx.android.synthetic.main.car_service_overview.*

class CarServiceOverview : Fragment() {
    private var _binding: CarServiceOverviewBinding? = null
    lateinit var adapter:CarAdapter
    private var baseContext:Context? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = CarServiceOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseContext = view.context
        adapter = CarAdapter(view.context)
        car_item_list.layoutManager = LinearLayoutManager(view.context)
        car_item_list.adapter = adapter

        binding.buttonAddCar.setOnClickListener {
            findNavController().navigate(R.id.car_service_overview_to_add_car)
        }

        binding.buttonTakePhoto.setOnClickListener {
            findNavController().navigate(R.id.car_service_overview_to_take_photo)
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