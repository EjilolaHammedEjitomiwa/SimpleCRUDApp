package com.geodeveloper.simplecrudapp.adapter

import android.content.Context
import android.view.*
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.geodeveloper.simplecrudapp.MainActivity
import com.geodeveloper.simplecrudapp.R
import com.geodeveloper.simplecrudapp.database.tables.Subscriber
import kotlinx.android.synthetic.main.activity_main.*
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;


class SubscriberAdapter (val context: Context, val itemLists: ArrayList<Subscriber>): RecyclerView.Adapter<SubscriberAdapter.ViewHolder?>() {
    private var gestureDetector: GestureDetector? = null
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
        holder.itemView.setOnClickListener {
            if (context is MainActivity) {
                context.subcriberID = item.id
                context.main_emailEditext.setText(item.email)
                context.main_nameEditext.setText(item.name)
                context.saveOrUpdateTag = "update"
                context.main_saveBtn.text = "update"
            }

        }
        holder.itemView.setOnLongClickListener {
            if (context is MainActivity) {
             context.deleteSubscriber().execute(Subscriber(item.id,item.name,item.email))
            }
            return@setOnLongClickListener true
        }
    }

    inner class ViewHolder(@NonNull itemView: View): RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.subcriber_design_name)
        val email: TextView = itemView.findViewById(R.id.subcriber_design_email)

    }
}