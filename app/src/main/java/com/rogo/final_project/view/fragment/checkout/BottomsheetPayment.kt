package com.rogo.final_project.view.fragment.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentBottomsheetPaymentBinding


class BottomsheetPayment : BottomSheetDialogFragment() {


    lateinit var binding: FragmentBottomsheetPaymentBinding
    companion object {
        val bottomTag : String = "bottomTag"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomsheetPaymentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnClose.setOnClickListener {
            dismiss()
            findNavController().navigate(R.id.homeFragment2)
        }
    }


}