package com.rogo.final_project.view.fragment.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentCheckoutBioPenumpangBinding

class CheckoutBioPenumpangFragment : Fragment() {

    lateinit var binding : FragmentCheckoutBioPenumpangBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCheckoutBioPenumpangBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val title = resources.getStringArray(R.array.title)
//        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, title)
//        binding.auto.setAdapter(arrayAdapter)
    }
}