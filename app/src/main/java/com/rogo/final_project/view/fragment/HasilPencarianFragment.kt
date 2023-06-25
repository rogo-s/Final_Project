package com.rogo.final_project.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentHasilPencarianBinding
import com.rogo.final_project.view.Adapter.SearchAdapter
import com.rogo.final_project.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HasilPencarianFragment : Fragment() {

    lateinit var binding : FragmentHasilPencarianBinding
    private val viewModel : SearchViewModel by viewModels()
    private lateinit var searchAdapter: SearchAdapter

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
    }

}