package com.example.listapitest

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listapitest.databinding.CarRentalsOverviewBinding
import kotlinx.android.synthetic.main.car_rentals_overview.*


class CarRentalsOverview : Fragment() {

    private var _binding: CarRentalsOverviewBinding? = null
    private lateinit var adapter:CarAdapter
    private var baseContext:Context? = null
    private val binding get() = _binding!!
    private val sharedPrefFile = "kotlinsharedpreference"
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var edit: SharedPreferences.Editor

    private lateinit var maxKilometers: String;
    private lateinit var maxCost: String;

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
        sharedPreferences = view.context.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
        edit = sharedPreferences.edit()

        maxKilometers = sharedPreferences.getString("max_kilometers_input", "") ?: "";
        maxCost = sharedPreferences.getString("max_cost_input", "") ?: "";

        max_kilometers_input.setText(maxKilometers);
        max_cost_input.setText(maxCost);

        car_item_list.layoutManager = LinearLayoutManager(view.context)
        car_item_list.adapter = adapter

        binding.buttonCarMap.setOnClickListener {
            findNavController().navigate(R.id.car_rentals_overview_to_car_map)
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
        val max_kilometers_input = max_kilometers_input.text.toString();
        val max_cost_input = max_cost_input.text.toString();

        edit.putString("max_kilometers_input", max_kilometers_input)
        edit.putString("max_cost_input", max_cost_input)
        edit.apply()

        adapter.getCars(max_kilometers_input, max_cost_input)
    }
}