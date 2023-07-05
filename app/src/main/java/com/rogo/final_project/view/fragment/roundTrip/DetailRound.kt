package com.rogo.final_project.view.fragment.roundTrip

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentDetailDepatureBinding
import com.rogo.final_project.databinding.FragmentDetailRoundBinding
import com.rogo.final_project.util.Utill
import com.rogo.final_project.view.Adapter.SectionPagerAdapter
import com.rogo.final_project.viewmodel.DetailViewModel
import com.rogo.final_project.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailRound : Fragment() {
    lateinit var binding: FragmentDetailRoundBinding
    lateinit var DetailVm : DetailViewModel
    private val homeViewModel : HomeViewModel by viewModels()
    lateinit var sharedPref : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailRoundBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        val id = arguments?.getInt("id")
        val hargaPergi = arguments?.getInt("hargaPergi")
        val hargaPulang = arguments?.getInt("hargaPulang")
        DetailVm = ViewModelProvider(this).get(DetailViewModel::class.java)

        Log.d("DetailPenerbanganPP", "Harga: $hargaPergi")
        Log.d("DetailPenerbanganPP", "Harga: $hargaPulang")


        val sectionPagerAdapter = SectionPagerAdapter(activity as AppCompatActivity)
        binding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(binding.tabs,binding.viewPager){
                tab,position ->  tab.text = resources.getString(
            TAB_TITLES[position]
        )
        }.attach()

        val priceTotal = hargaPergi?.plus(hargaPulang!!)
        binding.txtHargaTotal.text = Utill.getPriceIdFormat(priceTotal!!)

        binding.btnPilih.setOnClickListener {
            homeViewModel.saveIdTicket(id!!)
            if (sharedPref.getString("token", "").toString().isNotEmpty()) {
                if (findNavController().currentDestination!!.id == R.id.detailRound) {

                    findNavController().navigate(R.id.action_detailRound_to_checkoutBioFragment)
                }

            } else {
                val fragId = findNavController().currentDestination?.id
                findNavController().popBackStack(fragId!!, true)
                findNavController().navigate(R.id.bottomSheetCheckoutFragment)
            }
        }
    }

    companion object{
        private val TAB_TITLES = intArrayOf(
            R.string.str_pergi,
            R.string.str_pulang
        )
    }


}