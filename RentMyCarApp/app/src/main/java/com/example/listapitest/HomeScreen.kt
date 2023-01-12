package com.example.listapitest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.listapitest.databinding.HomescreenBinding


class HomeScreen : Fragment() {
    private var _binding: HomescreenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = HomescreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonRentals.setOnClickListener {
            findNavController().navigate(R.id.action_home_screen_to_car_rentals_overview)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}