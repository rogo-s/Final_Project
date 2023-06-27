package com.rogo.final_project.view.fragment.searching

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentPencarianFromBinding
import com.rogo.final_project.view.Adapter.HistorySeachAdapter
import com.rogo.final_project.view.Adapter.SearchFromAdapter
import com.rogo.final_project.view.model.data.search.Flight
import com.rogo.final_project.viewmodel.SearchViewModel

class PencarianFromFragment : Fragment() {
    private lateinit var binding : FragmentPencarianFromBinding
    private lateinit var homeVM: SearchViewModel
    private lateinit var sharedPreferences: SharedPreferences

    private val PREF_NAME = "SearchHistory"
    private val KEY_HISTORY = "search_history"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPencarianFromBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeVM = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)

        binding.ivSearch.setOnClickListener {
            val kotaSearch = binding.etSearch.text.toString()
            getSearch(requireContext(), kotaSearch)
            homeVM.saveCityFrom(kotaSearch)
        }
        binding.ivClose.setOnClickListener {
            findNavController().navigate(R.id.action_pencarianFromFragment_to_homeFragment2)
        }
        binding.tvDelete.setOnClickListener {
            clearSearchHistory(requireContext(), "city")
        }
        sharedPreferences = requireContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        showSearchHistory(requireContext())
    }

    private fun getSearch(context: Context, departureCity: String) {
        val searchText = binding.etSearch.text.toString().trim()
        if (searchText.isNotEmpty()) {
            homeVM.callGetSearchAirport(departureCity)
            homeVM.searching.observe(viewLifecycleOwner) { searchResults ->
//                val filteredResults = searchResults?.filter { Flight ->
//                    Flight.departureCity.contains(searchText, ignoreCase = true)
//
//               }
                val listFlight:ArrayList<Flight> = ArrayList()
                val data = searchResults.data
                for (i in data.indices) {
                   listFlight.add(data[i].flight)
                }
                val filterResult = listFlight.filter { element -> listFlight.count { it.arrivalCity == element.arrivalCity } > 1 }.distinct()


                showSearch(context, filterResult)
                saveSearchHistory(searchText)
            }
        }
    }

    private fun showSearch(context: Context, dataAirport: List<Flight>) {
        val searchAdapter = SearchFromAdapter(context, dataAirport)
        binding.rvCity.adapter = searchAdapter
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvCity.layoutManager = layoutManager
    }

    private fun saveSearchHistory(searchText: String) {
        val searchHistory = getSearchHistory().toMutableList()
        searchHistory.add(searchText)

        val editor = sharedPreferences.edit()
        editor.putStringSet(KEY_HISTORY, searchHistory.toSet())
        editor.apply()
    }

    private fun getSearchHistory(): Set<String> {
        return sharedPreferences.getStringSet(KEY_HISTORY, emptySet()) ?: emptySet()
    }

    private fun showSearchHistory(context: Context) {
        val searchHistory = getSearchHistory().toList()
        val searchHistoryAdapter = HistorySeachAdapter(context, searchHistory)
        binding.rvCity.adapter = searchHistoryAdapter
        binding.rvCity.layoutManager = LinearLayoutManager(context)


        searchHistoryAdapter.onItemClick = { searchText ->
            binding.etSearch.setText(searchText)
            getSearch(context, searchText)
        }
    }

    private fun clearSearchHistory(context: Context, city: String) {
        val editor = sharedPreferences.edit()
        editor.remove(KEY_HISTORY)
        editor.apply()

        showSearchHistory(context)
    }

}