package com.rogo.final_project.view.Adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.rogo.final_project.R
import com.rogo.final_project.databinding.ItemDestinasiBinding
import com.rogo.final_project.view.model.data.search.Flight

class SearchFromAdapter(private val context: Context, private val listairport : List<Flight>) : RecyclerView.Adapter<SearchFromAdapter.ViewHolder>() {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefsFrom", Context.MODE_PRIVATE)
    class ViewHolder (val binding: ItemDestinasiBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindSearch(item: Flight, sharedPreferences: SharedPreferences) {
            binding.tvDestination.setOnClickListener {
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("keyFrom", item.departureCity)
                editor.apply()

                val navController = Navigation.findNavController(binding.root)
                navController.previousBackStackEntry?.savedStateHandle?.set("selected_destination", item.departureCity)
                navController.navigate(R.id.action_pencarianFromFragment_to_homeFragment2)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemDestinasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listairport[position]
        holder.bindSearch(item, sharedPreferences)
        holder.binding.tvDestination.text = item.departureCity

        holder.itemView.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            // Menyimpan data ke SharedPreferences
            editor.putString("keyFrom", item.departureCity)
            editor.apply()

            Navigation.findNavController(it).navigate(R.id.action_pencarianFromFragment_to_homeFragment2)
        }

    }

    override fun getItemCount(): Int {
        return listairport.size
    }
}