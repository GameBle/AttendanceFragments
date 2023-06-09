package com.example.attendance

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate



class BarGraphFragment : Fragment() {
    private lateinit var barChart: BarChart
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_bar_graph, container, false)
        barChart = rootView.findViewById<BarChart>(R.id.barChart)
        barChart.setBackgroundColor(Color.parseColor("#4F4F4F"))
        setupBarChart()
        return rootView
    }

    private fun setupBarChart() {
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, 65f))
        entries.add(BarEntry(1f, 85f))
        entries.add(BarEntry(2f, 75f))
        entries.add(BarEntry(3f, 100f))
        entries.add(BarEntry(4f, 50f))

        val labels = listOf("label1", "label2", "label3", "label4", "label5")

        val barDataSet = BarDataSet(entries, "Values")
        barDataSet.color = Color.rgb(0, 128, 128) // Set the color to teal
        barDataSet.valueTextSize = 14f
        barDataSet.valueTextColor = Color.GREEN

        var barData = BarData(barDataSet)
        barChart.data = barData

        // Configure the X-axis
        val xAxis = barChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(true)
        xAxis.textSize = 12f
        xAxis.textColor = Color.GREEN

        // Configure the Y-axis
        val yAxis = barChart.axisLeft
        yAxis.setDrawGridLines(true)
        yAxis.setDrawAxisLine(true)
        yAxis.textSize = 12f
        yAxis.textColor = Color.GREEN
        barChart.axisRight.isEnabled = false

        barChart.setFitBars(true)
        barChart.invalidate()
        barChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry?, h: Highlight?) {
                if (e != null) {
                    val calendarFragment = CalendarFragment()
                    val fragmentTransaction =
                        requireActivity().supportFragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.fragment_container, calendarFragment)
                    fragmentTransaction.commit()
                }
            }
            override fun onNothingSelected() {
                // Handle the case when no bar is selected
            }
        })
    }
}




