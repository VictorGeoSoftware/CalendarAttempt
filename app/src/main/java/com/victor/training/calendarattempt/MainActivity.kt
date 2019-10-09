package com.victor.training.calendarattempt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.victor.training.calendarattempt.adapters.CalendarAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        view_pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val monthAdapter = CalendarAdapter()
        view_pager.adapter = monthAdapter

        view_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

            }
        })
    }

}
