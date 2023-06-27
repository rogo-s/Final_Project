package com.rogo.final_project.view.fragment.home

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentHomeBinding
import com.rogo.final_project.view.Adapter.HomeAdapter
import com.rogo.final_project.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter
    private var tanggalKembali = "Tanggal"
    private var tanggalPergi:String? = null
    private lateinit var pref : SharedPreferences
    private lateinit var homeViewModel: HomeViewModel

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

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.getFlights()

        val dateNowReturn = homeViewModel.getArrivedDate()
        val dateNowDeparture = homeViewModel.getDepartureDate()
        //set date
        binding.etReturn.setText(dateNowReturn)
        binding.etDepature.setText(dateNowDeparture)


        getSetPenumpang()
        datePickerReturn()
        datePickerDeparture()


        homeAdapter = HomeAdapter()
        binding.rvDestinasi.layoutManager = layoutManager
        homeViewModel.flight.observe(viewLifecycleOwner) {
            homeAdapter.differ.submitList(it.flights)
        }
        homeAdapter = HomeAdapter()

        binding.rvDestinasi.adapter = homeAdapter


//        binding.etPassengers.setOnClickListener {
//            SetPenempunganFragment().show(
//                requireActivity().supportFragmentManager,
//                SetPenempunganFragment.bottomTag
//            )
//        }

        binding.etPassengers.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_setPenempunganFragment)
        }

//        binding.tvJakarta.setOnClickListener {
//            BottomSheetPencarianFragment().show(
//                requireActivity().supportFragmentManager,
//                BottomSheetPencarianFragment.bottomTag
//            )
//        }
//        binding.etReturn.setOnClickListener {
//            BottomSheetDatePickerFragment().show(
//                requireActivity().supportFragmentManager,
//                BottomSheetDatePickerFragment.bottomTag
//            )
//        }
        binding.btnSwitch.setOnCheckedChangeListener { p0, isChecked ->
            if (isChecked) {
                homeViewModel.saveselected(true)
                binding.etReturn.visibility = View.VISIBLE

            } else {
                homeViewModel.saveselected(false)
                binding.etReturn.visibility = View.GONE

            }
        }
        val getCheck = homeViewModel.getCheckedSwitch()
        Log.d("Beranda Fragment","$getCheck")
        binding.btnSwitch.isChecked = getCheck

        //search ke halaman pencarian kota/negara
        binding.tvJakarta.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_pencarianFromFragment)
        }
        pref = requireContext().getSharedPreferences("MyPrefsFrom", Context.MODE_PRIVATE)
        val selectedDestination = pref.getString("keyFrom","")
        binding.tvJakarta.text = selectedDestination


        binding.tvMelbourne.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_pencarianToFragment)
        }
        val prefTo = requireContext().getSharedPreferences("MyPrefsTo", Context.MODE_PRIVATE)
        val selectedDestinationTo = prefTo.getString("keyTo","")
        binding.tvMelbourne.text = selectedDestinationTo

        //sear kelas
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

    @RequiresApi(Build.VERSION_CODES.N)
    private fun datePickerDeparture() {
        binding.etDepature.setOnClickListener {
            val nameMonth = namaMonth()
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    val bulan =nameMonth[month]
                    tanggalPergi = "$dayOfMonth $bulan+1 $year"
                    binding.etDepature.setText(tanggalPergi)
                },
                year, month, day,
            )
            datePickerDialog.show()
            datePickerDialog.setOnDateSetListener { datePicker, _, _, _ ->
                val bulanDeparture = nameMonth[datePicker.month]
                val month = datePicker.month
                val tahunDeparture = datePicker.year
                val hariDeparture = datePicker.dayOfMonth
                val tanggalDeparture = "$tahunDeparture-${month+1}-$hariDeparture"
                homeViewModel.saveDepartureDate(tanggalDeparture)
                findNavController().navigate(R.id.homeFragment2)
            }
            datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(R.color.darkblue05))
            datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(resources.getColor(R.color.darkblue05))

        }
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun datePickerReturn(){
        binding.etReturn.setOnClickListener {
            val nameMonth = namaMonth()

            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
                val bulan =nameMonth[month]
                tanggalPergi = "$dayOfMonth $bulan+1 $year"
                binding.etReturn.text = tanggalPergi
            },
                year, month, day,
            )
            datePickerDialog.show()
            datePickerDialog.setOnDateSetListener { datePicker, _, _, _ ->
                val nameMonth = namaMonth()
                val bulan =nameMonth[datePicker.month]
                val month = datePicker.month
                val tahun = datePicker.year
                val hari = datePicker.dayOfMonth
                val tanggalPilihan = "$tahun-${month+1}-$hari"
                homeViewModel.saveDatePref(tanggalPilihan)
                findNavController().navigate(R.id.homeFragment2)
            }
            datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(R.color.darkblue05))
            datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(resources.getColor(R.color.darkblue05))
        }
    }


    private fun namaMonth(): ArrayList<String> {
        val nameMonth = ArrayList<String>()
        nameMonth.add("Januari")
        nameMonth.add("Februari")
        nameMonth.add("Maret")
        nameMonth.add("April")
        nameMonth.add("Mei")
        nameMonth.add("Juni")
        nameMonth.add("Juli")
        nameMonth.add("Agustus")
        nameMonth.add("September")
        nameMonth.add("Oktober")
        nameMonth.add("November")
        nameMonth.add("Desember")
        return nameMonth
    }

    private fun getSetPenumpang(){
        val dewasa = homeViewModel.getPenumpangDewasa()
        val anak = homeViewModel.getPenumpangAnak()
        val bayi = homeViewModel.getPenumpangBayi()
        val total = dewasa + anak + bayi
        binding.etPassengers.text = "$total Penumpang"
    }



}