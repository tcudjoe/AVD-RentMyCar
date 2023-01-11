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
import com.example.listapitest.databinding.FragmentCarListBinding
import kotlinx.android.synthetic.main.fragment_car_list.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CarListFragment : Fragment() {

    private var _binding: FragmentCarListBinding? = null
    lateinit var adapter:CarAdapter
    private var baseContext:Context? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCarListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseContext = view.context
        adapter = CarAdapter(view.context)

        car_item_list.layoutManager = LinearLayoutManager(view.context)
        car_item_list.adapter = adapter

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.backButtonHuurder.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_homeScreen)
        }

        binding.buttonMap.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_mapsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.refresh -> {
            adapter.refreshCars()
            Toast.makeText(this.baseContext, "Refreshed", Toast.LENGTH_LONG).show()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}