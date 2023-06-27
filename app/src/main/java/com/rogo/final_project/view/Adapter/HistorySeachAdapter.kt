package com.rogo.final_project.view.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rogo.final_project.R

class HistorySeachAdapter(private val context: Context, private val dat: List<String>) : RecyclerView.Adapter<HistorySeachAdapter.ViewHolder>(){
    var onItemClick: ((String) -> Unit)? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val searchItem: TextView = itemView.findViewById(R.id.tvDestination)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.history_search_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searchItem = dat[position]
        holder.searchItem.text = searchItem

        holder.itemView.setOnClickListener {
            // Handle klik item histori pencarian
            onItemClick?.invoke(searchItem)
        }
    }

    override fun getItemCount(): Int {
        return dat.size
    }

}