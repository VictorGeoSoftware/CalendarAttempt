package com.victor.training.calendarattempt.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.victor.training.calendarattempt.R
import com.victor.training.calendarattempt.data.Month
import kotlinx.android.synthetic.main.adapter_calendar_weekly.view.*

const val DAYS_IN_WEEK = 7

class CollapsedCalendarAdapter(month: Month) : RecyclerView.Adapter<CollapsedCalendarAdapter.CollapsedMonthViewHolder>() {
    private val weeksArrayList: List<List<String>> = month.dayList.chunked(DAYS_IN_WEEK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollapsedMonthViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_calendar_weekly, parent, false)
        return CollapsedMonthViewHolder(view)
    }

    override fun getItemCount(): Int = weeksArrayList.size

    override fun onBindViewHolder(holder: CollapsedMonthViewHolder, position: Int) {
        holder.bind(weeksArrayList[position])
    }


    inner class CollapsedMonthViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(week: List<String>) {
            val gridLayoutManager
                    = GridLayoutManager(itemView.context, DAYS_IN_WEEK, GridLayoutManager.VERTICAL, false)
            itemView.lst_weekly_adapter.layoutManager = gridLayoutManager
            val adapter = DaysAdapter(week)
            itemView.lst_weekly_adapter.adapter = adapter
        }
    }
}