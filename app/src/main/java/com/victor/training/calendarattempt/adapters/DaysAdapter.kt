package com.victor.training.calendarattempt.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.victor.training.calendarattempt.R
import kotlinx.android.synthetic.main.adapter_days.view.*

class DaysAdapter(private val daysArrayList: List<String>) : RecyclerView.Adapter<DaysAdapter.DaysViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_days, parent, false)
        return DaysViewHolder(view)
    }

    override fun getItemCount() = daysArrayList.size

    override fun onBindViewHolder(holder: DaysViewHolder, position: Int) {
        holder.bind(daysArrayList[position])
    }

    inner class DaysViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dayNumber: String) {
            itemView.txt_day_number.text = dayNumber
        }
    }
}