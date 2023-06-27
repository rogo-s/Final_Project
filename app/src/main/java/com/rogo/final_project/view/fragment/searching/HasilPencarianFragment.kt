package com.rogo.final_project.view.fragment.searching

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentHasilPencarianBinding
import com.rogo.final_project.view.Adapter.SearchAdapter
import com.rogo.final_project.viewmodel.HomeViewModel
import com.rogo.final_project.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HasilPencarianFragment : Fragment() {

    lateinit var binding : FragmentHasilPencarianBinding
    private val viewModel : SearchViewModel by viewModels()
    private val hmVm : HomeViewModel by viewModels()
    private lateinit var searchAdapter: SearchAdapter
    private var tanggalPergi:String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment'
        binding = FragmentHasilPencarianBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTickets()
        searchAdapter = SearchAdapter()
        binding.rvListItem.adapter = searchAdapter
        binding.rvListItem.layoutManager = LinearLayoutManager(requireContext())
        viewModel.search.observe(viewLifecycleOwner){
            searchAdapter.differ.submitList(it.tickets)

        }
//        val cityFrom = hmVm.getCityFrom()
//        val cityTo = hmVm.getCityTo()
//        val dewasa = hmVm.getPenumpangDewasa()
//        val departure = hmVm.getDepartureDate()
//        val arrived = hmVm.getArrivedDate()
//        val order = viewModel.getorder()
//        val anak = hmVm.getPenumpangAnak()
//        val bayi = hmVm.getPenumpangBayi()
//        val totalPassengers = dewasa + anak + bayi
//        val seatClass = HomeVm.getNamaKelas()
//        binding.appBar.text = "$cityFrom < > $cityTo - $totalPassengers Penumpang "
//
//        departureOnly(cityFrom, cityTo, departure,arrived)
//
//        dateToolbar(departure)

//        HomeVm.searchallticket(cityFrom!!, cityTo!!,departure!!,arrived!!)

//        binding.ivBackBeranda.setOnClickListener {
//            findNavController().navigate(R.id.action_hasilPencarianFragment_to_homeFragment2)
//        }

//        binding.btnFilterHarga.setOnClickListener {
//            filterharga(cityFrom,cityTo,departure,arrived,order)
//        }

//        binding.btnFilterHarga.setOnClickListener {
//            val modalBottomSheet = FilterHarga()
//            modalBottomSheet.show(requireFragmentManager(),FilterHarga.TAG)
//            //     val intent = Intent(this, GetFilterPrice::class.java)
//            //      findNavController().navigate(R.id.action_hasilPencarianFragment_to_dialogFilter)
//        }

    }

//    private fun dateToolbar(dateDeparture: String?) {
//        if (dateDeparture != null) {
//            binding.etDate.setText(dateDeparture)
//            binding.etDate.setOnClickListener {
//                val calendar = Calendar.getInstance()
//                val year = calendar.get(Calendar.YEAR)
//                val month = calendar.get(Calendar.MONTH)
//                val day = calendar.get(Calendar.DAY_OF_MONTH)
//
//                val datePickerDialog = DatePickerDialog(
//                    requireContext(),
//                    { _, year, month, dayOfMonth ->
//
//                        tanggalPergi = "$year-${month + 1}-$dayOfMonth"
//                        binding.etDate.setText(tanggalPergi)
//                    },
//                    year, month, day,
//                )
//                datePickerDialog.show()
//                datePickerDialog.setOnDateSetListener { datePicker, _, _, _ ->
//                    val month = datePicker.month
//                    val tahunDeparture = datePicker.year
//                    val hariDeparture = datePicker.dayOfMonth
//                    val tanggalDeparture = "$tahunDeparture-${month + 1}-$hariDeparture"
//                    hmVm.saveDepartureDate(tanggalDeparture)
//                    findNavController().navigate(R.id.hasilPencarianFragment)
//                }
//                    datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)
//                        .setTextColor(resources.getColor(R.color.darkblue_05))
//                    datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE)
//                        .setTextColor(resources.getColor(R.color.darkblue_05))
//            }
//        }
//    }

//    private fun departureOnly(
//        cityFrom: String?,
//        cityTo: String?,
//        dateDeparture: String?,
//        arrivedDate:String?
//    ) {
//        viewModel.searchallticket(cityFrom!!,cityTo!!,dateDeparture!!,arrivedDate!!)
//        viewModel.livedatasearchallticket.observe(viewLifecycleOwner){
//            binding.rvTanggalPenerbangan.apply {
//                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
//            }
//        }
//    }
}