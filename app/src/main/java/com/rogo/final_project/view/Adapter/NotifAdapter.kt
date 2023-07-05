package com.rogo.final_project.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rogo.final_project.databinding.ItemHasilPencarianBinding
import com.rogo.final_project.databinding.NotificationItemBinding
import com.rogo.final_project.view.Adapter.HasilPencarianAdapter
import com.rogo.final_project.view.Adapter.HomeAdapter
import com.rogo.final_project.view.model.data.search.Data


class NotifAdapter(val listairport : List<Data>,  private val onSelect:(Data) -> Unit) : RecyclerView.Adapter<NotifAdapter.ViewHolder>() {

    class ViewHolder(val binding : NotificationItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = NotificationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HasilPencarianAdapter.ViewHolder, position: Int) {
        holder.binding.tvJamBerangkat.text = listairport[position].flight.departureTime
        holder.binding.tvInisial.text = listairport[position].flight.departureCity
        holder.binding.tvEstimasiWaktu.text = listairport[position].flight.duration
        holder.binding.tvJamTiba.text = listairport[position].flight.arrivalTime
        holder.binding.tvInisialDua.text = listairport[position].flight.arrivalCity
        holder.binding.tvHarga.text = listairport[position].price.toString()
        holder.binding.tvClassSeat.text = listairport[position].classSeat
        holder.binding.tvClassPesawat.text = listairport[position].flight.airline

        holder.binding.cvHasilPencarian.setOnClickListener {
            onSelect(listairport[position])
//            val id = listairport[position].id
//            val bundle = Bundle()
//            bundle.putInt("id", id!!)
//            Navigation.findNavController(item).navigate(R.id.action_hasilPencarianFragment_to_detailNonLoginFragment5, bundle)
        }

    }

    override fun getItemCount(): Int {
        return listairport.size
    }
}