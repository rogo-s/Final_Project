package com.rogo.final_project.view.Adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rogo.final_project.R
import com.rogo.final_project.databinding.DestinasiItemBinding
import com.rogo.final_project.databinding.ItemHasilPencarianBinding
import com.rogo.final_project.view.model.data.flight.Flight
import com.rogo.final_project.view.model.data.ticket.Ticket
import com.rogo.final_project.view.model.data.ticket.TicketsItem

class SearchAdapter() : RecyclerView.Adapter<SearchAdapter.ItemViewHolder>() {

//    val onSelect:(TicketsItem) -> Unit
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TicketsItem>() {

        override fun areItemsTheSame(oldItem: TicketsItem, newItem: TicketsItem): Boolean {
            return (oldItem == newItem)
        }

        override fun areContentsTheSame(oldItem: TicketsItem, newItem: TicketsItem): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    class ItemViewHolder(private val binding: ItemHasilPencarianBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TicketsItem) {
            with(binding) {
                // tambahin jam, keberangkatan, jam kepulangan, nama pesawat, tujuan
                tvClassPesawat.text = item.flight?.airline + " - " + item.jsonMemberClass
                tvJamBerangkat.text = item.flight?.departureTime?.substring(0, 5)
                tvJamTiba.text = item.flight?.arrivalTime?.substring(0, 5)
                tvInisial.text = item.flight?.departureCity
                tvInisialDua.text = item.flight?.arrivalCity
                tvHarga.text = item.price.toString()

//                binding.cvHasilPencarian.setOnClickListener {
//                    val id = item.id
//                    val bundle = Bundle()
//                    bundle.putInt("id", id!!)
//                    it.findNavController().navigate(R.id.action_hasilPencarianFragment_to_detailNonLoginFragment5, bundle)
//                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHasilPencarianBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)

    }
}