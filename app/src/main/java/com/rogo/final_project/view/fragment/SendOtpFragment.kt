package com.rogo.final_project.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentSendOtpBinding

class SendOtpFragment : Fragment() {
    lateinit var binding : FragmentSendOtpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSendOtpBinding.inflate(inflater, container, false)
        return binding.root
    }
}