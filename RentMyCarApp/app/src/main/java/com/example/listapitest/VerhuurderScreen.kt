package com.example.listapitest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.listapitest.databinding.FragmentSecondBinding
import com.example.listapitest.databinding.FragmentVerhuurderscreenBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class VerhuurderScreen : Fragment() {

    private var _binding: FragmentVerhuurderscreenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentVerhuurderscreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.terugButtonVerhuurder.setOnClickListener {
            findNavController().navigate(R.id.action_verhuurderScreen_to_homeScreen)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}