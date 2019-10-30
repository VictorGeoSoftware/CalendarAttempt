package com.victor.training.calendarattempt.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.victor.training.calendarattempt.R
import com.victor.training.calendarattempt.data.Month
import kotlinx.android.synthetic.main.adapter_calendar_month.view.*
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.YearMonth
import java.util.*
import kotlin.collections.ArrayList

class CalendarAdapter(
    private val monthsToGenerate: Int = 12
) : RecyclerView.Adapter<CalendarAdapter.MonthViewHolder>() {
    private val monthsArrayList: ArrayList<Month> = ArrayList()

    init {
        createMonthList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_calendar_month, parent, false)
        return MonthViewHolder(view)
    }

    override fun getItemCount() = monthsToGenerate

    override fun onBindViewHolder(holder: MonthViewHolder, position: Int) {
        holder.bind(monthsArrayList[position])
    }

    private fun createMonthList() {
        for (monthIndex in 0..monthsToGenerate) {
            monthsArrayList.add(createMonth(Date(), monthIndex))
        }
    }

    private fun createMonth(date: Date, monthIndex: Int): Month {
        val dayNamesList = createWeekDays()
        val dateFormat = SimpleDateFormat("dd MM yyyy", Locale.getDefault())
        val dateString = dateFormat.format(date)
        val yearNumber = dateString.split(" ")[2].toInt()
        val currentMonthNumber = dateString.split(" ")[1].toInt() - 1
        val monthIndexToCreate = currentMonthNumber + monthIndex
        val monthFirstDayNumber = 1

        val calendar = GregorianCalendar(yearNumber, monthIndexToCreate, monthFirstDayNumber)

        // Days of the week
        val dayList = ArrayList<String>()
        dayList.addAll(dayNamesList)

        val calendarViewStartingDay = Calendar.getInstance(Locale.getDefault()).firstDayOfWeek

        if (calendarViewStartingDay == Calendar.MONDAY) {
            val firstDay = dayList[0]
            dayList.removeAt(0)
            dayList.add(dayList.size, firstDay)
        }

        // Blank positions
            // See definition, but it's received from 1..7
        val firstDayPosition = calendar.get(Calendar.DAY_OF_WEEK) - 2

        for (blank in 0..firstDayPosition) {
            dayList.add("")
        }

        val daysInThisMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        for (day in monthFirstDayNumber..daysInThisMonth) {
            dayList.add(day.toString())
        }

        return Month(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()), dayList)
    }

    private fun createWeekDays(): List<String> {
        val symbols = DateFormatSymbols()
        return symbols.shortWeekdays.filter { it.isNotEmpty() }.toList()
    }

    inner class MonthViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(month: Month) {
            itemView.txt_month_name.text = month.monthName

            val gridLayoutManager
                    = GridLayoutManager(itemView.context, 7, GridLayoutManager.VERTICAL, false)
            itemView.lst_days.layoutManager = gridLayoutManager
            val adapter = DaysAdapter(month.dayList)
            itemView.lst_days.adapter = adapter
        }
    }
}