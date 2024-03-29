package com.rogo.final_project.view.fragment.checkout

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
import com.rogo.final_project.databinding.FragmentCheckoutRoundBinding
import com.rogo.final_project.util.Utill
import com.rogo.final_project.view.Adapter.SectionPagerAdapter
import com.rogo.final_project.viewmodel.DetailViewModel
import com.rogo.final_project.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutRoundFragment : Fragment() {

    lateinit var binding : FragmentCheckoutRoundBinding
    lateinit var DetailVm : DetailViewModel
    private val homeViewModel : HomeViewModel by viewModels()
    lateinit var sharedPref : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCheckoutRoundBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)

//        val id = arguments?.getInt("id")
//        val hargaPergi = arguments?.getInt("hargaPergi")
//        val hargaPulang = arguments?.getInt("hargaPulang")

        DetailVm = ViewModelProvider(this).get(DetailViewModel::class.java)

        val idDepature = homeViewModel.getIdDep()
        DetailVm.getdetailticket(idDepature!!)
        val idReturn = homeViewModel.getIdReturn()
        DetailVm.getdetailticket(idReturn!!)

//        Log.d("DetailPenerbanganPP", "Harga: $hargaPergi")
//        Log.d("DetailPenerbanganPP", "Harga: $hargaPulang")

        val sectionPagerAdapter = CheckoutDetailAdapterRound(activity as AppCompatActivity)
        binding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(binding.tabs,binding.viewPager){
                tab,position ->  tab.text = resources.getString(
            TAB_TITLES[position]
        )
        }.attach()

//        val priceTotal = hargaPergi?.plus(hargaPulang!!)
//        binding.totalPrice.text = Utill.getPriceIdFormat(priceTotal!!)

        binding.btnSave.setOnClickListener {
            homeViewModel.saveIdTicket(id!!)
            if (sharedPref.getString("token", "").toString().isNotEmpty()) {
                if (findNavController().currentDestination!!.id == R.id.checkoutRoundFragment) {

                    findNavController().navigate(R.id.action_checkoutRoundFragment_to_paymentFragment)
                }

            } else {
                val fragId = findNavController().currentDestination?.id
                findNavController().popBackStack(fragId!!, true)
                findNavController().navigate(R.id.bottomSheetCheckoutFragment)
            }
        }

        DetailVm.livedetailticket.observe(viewLifecycleOwner) {detail->
            binding.apply {
                val dewasa = homeViewModel.getPenumpangDewasa()
                val anak = homeViewModel.getPenumpangAnak()
                val bayi = homeViewModel.getPenumpangBayi()
                val total = dewasa + anak + bayi
                val priceDepature = detail.ticket.price
                val priceArrival = detail.ticket.price
                val totalHarga = priceDepature + priceArrival


                jumlah.text = "$total Passangers"
//                totalPrice.text = totalHarga.toString()

                val priceTotal = priceDepature?.plus(priceArrival!!)
                binding.totalPrice.text = Utill.getPriceIdFormat(priceTotal!!)
                binding.hargaSatu.text = Utill.getPriceIdFormat(priceTotal!!)

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