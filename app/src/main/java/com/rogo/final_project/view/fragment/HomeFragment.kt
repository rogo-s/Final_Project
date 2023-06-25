package com.rogo.final_project.view.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentHomeBinding
import com.rogo.final_project.view.Adapter.HomeAdapter
import com.rogo.final_project.view.model.data.DestinasiItem
import com.rogo.final_project.view.model.data.flight.ReqBodyFlight
import com.rogo.final_project.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter
    private var tanggalKembali: String? = null
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        homeViewModel.getFlights()
        getTanggalKembali()
        homeAdapter = HomeAdapter()
        binding.rvDestinasi.layoutManager = layoutManager
        homeViewModel.flight.observe(viewLifecycleOwner) {
            homeAdapter.differ.submitList(it.flights)
        }
        homeAdapter = HomeAdapter()

        binding.rvDestinasi.adapter = homeAdapter


        binding.etPassengers.setOnClickListener {
            SetPenempunganFragment().show(
                requireActivity().supportFragmentManager,
                SetPenempunganFragment.bottomTag
            )
        }
        binding.tvJakarta.setOnClickListener {
            BottomSheetPencarianFragment().show(
                requireActivity().supportFragmentManager,
                BottomSheetPencarianFragment.bottomTag
            )
        }
        binding.etReturn.setOnClickListener {
            BottomSheetDatePickerFragment().show(
                requireActivity().supportFragmentManager,
                BottomSheetDatePickerFragment.bottomTag
            )
        }
        binding.etSeat.setOnClickListener {
            BottomSheetKelasFragment().show(
                requireActivity().supportFragmentManager,
                BottomSheetKelasFragment.bottomTag
            )
        }
        binding.btnCariPenerbangan.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment2_to_hasilPencarianFragment)
        }

    }

    fun getTanggalKembali() {
        if (tanggalKembali == null) {
            binding.etDepature.setText("Pilih Tanggal")
        } else {
            binding.etDepature.setText(tanggalKembali)
            binding.etDepature.setTextColor(resources.getColor(R.color.black))

        }
    }


}