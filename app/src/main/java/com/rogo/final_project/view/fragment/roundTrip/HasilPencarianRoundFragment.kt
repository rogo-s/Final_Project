package com.rogo.final_project.view.fragment.roundTrip

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
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
import com.rogo.final_project.databinding.FragmentHasilPencarianRoundBinding
import com.rogo.final_project.view.Adapter.HasilPencarianAdapter
import com.rogo.final_project.view.Adapter.RoundTripAdapter
import com.rogo.final_project.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HasilPencarianRoundFragment : Fragment() {
    lateinit var binding : FragmentHasilPencarianRoundBinding
    private val hmVm : HomeViewModel by viewModels()
    lateinit var fromPref : SharedPreferences
    private lateinit var roundAdapter: RoundTripAdapter
    private var tanggalPergi:String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHasilPencarianRoundBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fromPref = requireContext().getSharedPreferences("keyFrom", Context.MODE_PRIVATE)
        val cityDepature = fromPref.getString("departureCity","")

        fromPref = requireContext().getSharedPreferences("keyTo", Context.MODE_PRIVATE)
        val cityArrival = fromPref.getString("arrivalCity","")
//        viewModel.getTickets()
//        searchAdapter = SearchAdapter()
//        binding.rvListItem.adapter = searchAdapter
//        binding.rvListItem.layoutManager = LinearLayoutManager(requireContext())
//        viewModel.search.observe(viewLifecycleOwner){
//            searchAdapter.differ.submitList(it.tickets)
//
//        }
        val cityFrom = hmVm.getCityFrom()
        val cityTo = hmVm.getCityTo()
        val dewasa = hmVm.getPenumpangDewasa()
        val departure = hmVm.getDepartureDate()
        val arrived = hmVm.getArrivedDate()
//        val order = viewModel.getorder()
        val anak = hmVm.getPenumpangAnak()
        val bayi = hmVm.getPenumpangBayi()
        val totalPassengers = dewasa + anak + bayi
//        val seatClass = HomeVm.getNamaKelas()
        binding.toolbarTitle.text = "$cityFrom < > $cityTo - $totalPassengers Penumpang "

        departureOnly(cityFrom, cityTo, departure,arrived)

        dateToolbar(departure)


    }

    private fun dateToolbar(dateDeparture: String?) {
        if (dateDeparture != null) {
            binding.etDate.setText(dateDeparture)
            binding.etDate.setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    { _, year, month, dayOfMonth ->

                        tanggalPergi = "$year-${month + 1}-$dayOfMonth"
                        binding.etDate.setText(tanggalPergi)
                    },
                    year, month, day,
                )
                datePickerDialog.show()
                datePickerDialog.setOnDateSetListener { datePicker, _, _, _ ->
                    val month = datePicker.month
                    val tahunDeparture = datePicker.year
                    val hariDeparture = datePicker.dayOfMonth
                    val tanggalDeparture = "$tahunDeparture-${month + 1}-$hariDeparture"
                    hmVm.saveDepartureDate(tanggalDeparture)
                    findNavController().navigate(R.id.hasilPencarianFragment)
                }
                datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)
                    .setTextColor(resources.getColor(R.color.darkblue_05))
                datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE)
                    .setTextColor(resources.getColor(R.color.darkblue_05))
            }
        }
    }

    private fun departureOnly(
        cityFrom: String?,
        cityTo: String?,
        dateDeparture: String?,
        arrivedDate:String?
    ) {
        hmVm.searchallticket(cityFrom!!,cityTo!!,dateDeparture!!,arrivedDate!!)

        hmVm.livedatasearchallticket.observe(viewLifecycleOwner){
            binding.rvListItem.apply {
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                roundAdapter = RoundTripAdapter(it) {itemTicket ->
                    val id = itemTicket.id
                    val hargaPergi = itemTicket.price
                    val bundle = Bundle()
                    hmVm.saveIdDeparture(id)
                    bundle.putInt("idDep", id)
                    bundle.putInt("pricePergi",hargaPergi)
                    findNavController().navigate(R.id.action_hasilPencarianRoundFragment_to_hasilPencarianReturnFragment, bundle)
                }
                adapter = roundAdapter
                isNestedScrollingEnabled = false
            }

//            binding.rvListItem.apply {
//                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
//                adapter  = searchAdapter
//            }
//            binding.rvListItem.apply {
//                val searchAdapter = RoundTripAdapter(it)
//
//                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
//                adapter = searchAdapter
//            }

        }
    }

}