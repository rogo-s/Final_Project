package com.rogo.final_project.view.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentSetPenempunganBinding
import com.rogo.final_project.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SetPenempunganFragment : BottomSheetDialogFragment() {
    private lateinit var binding : FragmentSetPenempunganBinding
    private lateinit var homeViewModel : HomeViewModel
//    companion object{
//        val bottomTag :String = "TAG_PENUMPANG"
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSetPenempunganBinding.inflate(layoutInflater, container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        setClose()
        Log.d("Sheet set Penumpang", "onViewCreated")

        val jmlDewasa = homeViewModel.getPenumpangDewasa()
        val jmlAnak = homeViewModel.getPenumpangAnak()
        val jmlBayi = homeViewModel.getPenumpangBayi()

        val dewasa = setDewasa(jmlDewasa)
        val anak = setAnak(jmlAnak)
        val bayi = setBayi(jmlBayi)


        binding.passangersBaby.text = bayi.toString()
        binding.passangerChild.text = dewasa.toString()
        binding.passangerAdult.text = anak.toString()

        binding.btnDaftar.setOnClickListener {
            val tvDewasa = binding.passangerAdult.text.toString()
            val tvBayi = binding.passangersBaby.text.toString()
            val tvAnak = binding.passangerChild.text.toString()

            homeViewModel.savePenumpangPreferences(tvDewasa.toInt(), tvAnak.toInt(), tvBayi.toInt())

            findNavController().navigate(R.id.action_setPenempunganFragment_to_homeFragment2)


        }
    }

    private fun setClose() {
        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }

    private fun setBayi(tiketBayi:Int):Int {
        binding.passangersBaby.text = tiketBayi.toString()
        var tiketBayi1 = tiketBayi

        binding.addPassangerBaby.setOnClickListener {
            tiketBayi1 += 1
            binding.passangersBaby.text = tiketBayi1.toString()
        }

        binding.decPassangerBaby.setOnClickListener {
            if (tiketBayi1 > 0) {
                tiketBayi1 -= 1
                binding.passangersBaby.text = tiketBayi1.toString()
            }
        }
        return tiketBayi1
    }

    private fun setAnak(tiketAnak:Int):Int{
        binding.passangerChild.text = tiketAnak.toString()
        var tiketAnak1 = tiketAnak
        binding.addPassangerChild.setOnClickListener {
            tiketAnak1 += 1
            binding.passangerChild.text = tiketAnak1.toString()
        }

        binding.decPassangerChild.setOnClickListener {
            if (tiketAnak1 > 0) {
                tiketAnak1 -= 1
                binding.passangerChild.text = tiketAnak1.toString()
            }
        }
        return tiketAnak1
    }

    private fun setDewasa(tiketDewasa:Int):Int{
        binding.passangerAdult.text = tiketDewasa.toString()

        var tiketDewasa1 = tiketDewasa
        binding.addPassangerAdult.setOnClickListener {
            tiketDewasa1 += 1
            binding.passangerAdult.text = tiketDewasa1.toString()

        }

        binding.decPassangerAdult.setOnClickListener {
            if (tiketDewasa1 > 0) {
                tiketDewasa1 -= 1
                binding.passangerAdult.text = tiketDewasa1.toString()
            }
        }
        return tiketDewasa1
    }

    override fun onResume() {
        super.onResume()

        val jmlDewasa = homeViewModel.getPenumpangDewasa()
        val jmlAnak = homeViewModel.getPenumpangAnak()
        val jmlBayi = homeViewModel.getPenumpangBayi()

        val dewasa = setDewasa(jmlDewasa)
        val anak = setAnak(jmlAnak)
        val bayi = setBayi(jmlBayi)


        binding.passangersBaby.text = bayi.toString()
        binding.passangerChild.text = dewasa.toString()
        binding.passangerAdult.text = anak.toString()

        binding.btnDaftar.setOnClickListener {
            val tvDewasa = binding.passangerAdult.text.toString()
            val tvBayi = binding.passangersBaby.text.toString()
            val tvAnak = binding.passangerChild.text.toString()
            homeViewModel.savePenumpangPreferences(tvDewasa.toInt(), tvAnak.toInt(), tvBayi.toInt())
            findNavController().navigate(R.id.action_setPenempunganFragment_to_homeFragment2)
        }
    }

}