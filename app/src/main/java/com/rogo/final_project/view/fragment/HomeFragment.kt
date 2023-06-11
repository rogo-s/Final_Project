package com.rogo.final_project.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentHomeBinding
import com.rogo.final_project.view.Adapter.HomeAdapter
import com.rogo.final_project.view.model.data.DestinasiItem


class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding
    lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        binding.rvDestinasi.layoutManager = layoutManager

        val destinasiItem = listOf(
            DestinasiItem(R.drawable.image_destinasi,"Palembang -> Bali","Garuda","6 Juni 2023","Rp.3.650.000"),
            DestinasiItem(R.drawable.image_destinasi,"Palembang -> Bali","Garuda","6 Juni 2023","Rp.3.650.000"),
            DestinasiItem(R.drawable.image_destinasi,"Palembang -> Bali","Garuda","6 Juni 2023","Rp.3.650.000"),
            DestinasiItem(R.drawable.image_destinasi,"Palembang -> Bali","Garuda","6 Juni 2023","Rp.3.650.000"),
            DestinasiItem(R.drawable.image_destinasi,"Palembang -> Bali","Garuda","6 Juni 2023","Rp.3.650.000"),
            DestinasiItem(R.drawable.image_destinasi,"Palembang -> Bali","Garuda","6 Juni 2023","Rp.3.650.000"),
            DestinasiItem(R.drawable.image_destinasi,"Palembang -> Bali","Garuda","6 Juni 2023","Rp.3.650.000"),
            DestinasiItem(R.drawable.image_destinasi,"Palembang -> Bali","Garuda","6 Juni 2023","Rp.3.650.000"),
            DestinasiItem(R.drawable.image_destinasi,"Palembang -> Bali","Garuda","6 Juni 2023","Rp.3.650.000"),
            DestinasiItem(R.drawable.image_destinasi,"Palembang -> Bali","Garuda","6 Juni 2023","Rp.3.650.000"),
            DestinasiItem(R.drawable.image_destinasi,"Palembang -> Bali","Garuda","6 Juni 2023","Rp.3.650.000"),
            DestinasiItem(R.drawable.image_destinasi,"Palembang -> Bali","Garuda","6 Juni 2023","Rp.3.650.000")
        )
        homeAdapter = HomeAdapter(destinasiItem)
        binding.rvDestinasi.adapter = homeAdapter

        binding.etPassengers.setOnClickListener {
            SetPenempunganFragment().show(requireActivity().supportFragmentManager,SetPenempunganFragment.bottomTag)
        }
        binding.tvJakarta.setOnClickListener {
            BottomSheetPencarianFragment().show(requireActivity().supportFragmentManager,BottomSheetPencarianFragment.bottomTag)
        }
    }


}