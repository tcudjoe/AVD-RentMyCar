package com.example.listapitest

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listapitest.databinding.CarRentalsOverviewBinding
import kotlinx.android.synthetic.main.car_rentals_overview.*

class CarRentalsOverview : Fragment() {

    private var _binding: CarRentalsOverviewBinding? = null
    lateinit var adapter:CarAdapter
    private var baseContext:Context? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = CarRentalsOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseContext = view.context
        adapter = CarAdapter(view.context)

        car_item_list.layoutManager = LinearLayoutManager(view.context)
        car_item_list.adapter = adapter

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.car_rentals_overview_to_home_screen)
        }

        binding.maxCostInput.addTextChangedListener {
            getCars()
        }

        binding.maxKilometersInput.addTextChangedListener {
            getCars()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.refresh -> {
            getCars()
            Toast.makeText(this.baseContext, "Refreshed", Toast.LENGTH_LONG).show()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun getCars()
    {
        adapter.getCars(max_kilometers_input.text.toString(), max_cost_input.text.toString())
    }
}