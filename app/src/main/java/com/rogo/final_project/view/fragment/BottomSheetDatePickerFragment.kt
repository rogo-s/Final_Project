package com.rogo.final_project.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentBottomSheetDatePickerBinding
import java.text.SimpleDateFormat
import java.util.*


class BottomSheetDatePickerFragment : BottomSheetDialogFragment() {

    lateinit var binding : FragmentBottomSheetDatePickerBinding

    companion object{
        val bottomTag : String  = "TAGDATE"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBottomSheetDatePickerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setDatePicker.setOnClickListener {
            val datePickerRange = MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Select Date")
                .setSelection(
                    Pair(
                        MaterialDatePicker.thisMonthInUtcMilliseconds(),
                        MaterialDatePicker.todayInUtcMilliseconds()
                    )
                )
                .build()
            datePickerRange.show(requireActivity().supportFragmentManager,"date_picker")

            datePickerRange.addOnPositiveButtonClickListener {
                val simpleDateFormat = SimpleDateFormat("EEE, dd MMMM yyyy", Locale.getDefault())
                binding.setDatePicker.text = "${simpleDateFormat.format(it.first)}"
                binding.setDatePicker2.text = "${simpleDateFormat.format(it.second)}"
            }
        }


    }


}