package com.geodeveloper.simplecrudapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.geodeveloper.simplecrudapp.R
import com.geodeveloper.simplecrudapp.database.tables.Subscriber

class SubscriberAdapter (val context: Context, val itemLists: ArrayList<Subscriber>): RecyclerView.Adapter<SubscriberAdapter.ViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.subscriber_list_design,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemLists.size
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val item  = itemLists[position]
        holder.name.text = item.name
        holder.email.text = item.email
    }

    inner class ViewHolder(@NonNull itemView: View): RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.subcriber_design_name)
        val email: TextView = itemView.findViewById(R.id.subcriber_design_email)

    }
}