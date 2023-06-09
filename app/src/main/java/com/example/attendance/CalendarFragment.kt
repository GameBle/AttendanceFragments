package com.example.attendance

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class CalendarFragment : Fragment() {
    private val presentDays: List<Int> = listOf(1, 2, 3, 4, 5, 9, 11, 12, 14, 16, 18, 19, 20, 24, 25, 27, 28)
    private val absentDays: List<Int> = listOf(6, 8, 10, 13, 17, 21, 23, 26, 30)
    private val cancelledDays: List<Int> = listOf(7, 15, 22, 29) // Add the days on which classes were cancelled

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_calendar, container, false)
        rootView.setBackgroundColor(Color.parseColor("#4f4f4f"))
        setupCalendar(rootView)
        return rootView
    }

    private fun setupCalendar(rootView: View) {
        val calendarLayout = rootView.findViewById<LinearLayout>(R.id.calendarLayout)
        val monthYearTextView = rootView.findViewById<TextView>(R.id.monthYearTextView)

        val calendar = getMonthCalendar()
        val monthYearText = "June 2023"
        monthYearTextView.text = monthYearText
        monthYearTextView.setTextColor(Color.GREEN)

        for (week in calendar) {
            val weekLayout = LinearLayout(requireContext())
            weekLayout.orientation = LinearLayout.HORIZONTAL

            for (day in week) {
                val dayTextView = TextView(requireContext())
                val params = LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
                )
                dayTextView.layoutParams = params
                dayTextView.gravity = Gravity.CENTER
                dayTextView.text = day.toString()

                if (presentDays.contains(day)) {
                    dayTextView.setBackgroundResource(R.drawable.cell_background_present)
                } else if (absentDays.contains(day)) {
                    dayTextView.setBackgroundResource(R.drawable.cell_background_absent)
                } else if (cancelledDays.contains(day)) {
                    dayTextView.setBackgroundResource(R.drawable.cell_background_cancelled)
                } else {
                    dayTextView.setBackgroundColor(Color.TRANSPARENT)
                }
                weekLayout.addView(dayTextView)
            }

            calendarLayout.addView(weekLayout)
        }
    }

    private fun getMonthCalendar(): List<List<Int>> {
        val calendar = mutableListOf<List<Int>>()
        val daysInMonth = 30
        val daysPerRow = 7

        for (row in 0 until (daysInMonth / daysPerRow) + 1) {
            val week = mutableListOf<Int>()
            val startDay = row * daysPerRow + 1
            val endDay = startDay + daysPerRow

            for (day in startDay until endDay) {
                if (day <= daysInMonth) {
                    week.add(day)
                }
            }

            calendar.add(week)
        }

        return calendar
    }
}




