package com.example.attendance

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity: AppCompatActivity() {
    private lateinit var currentFragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create an instance of the BarGraphFragment
        val barGraphFragment = BarGraphFragment()

        // Add the BarGraphFragment to the activity
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, barGraphFragment)
            .commit()

        currentFragment =  barGraphFragment
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment is CalendarFragment) {
            // Navigate back to BarGraphFragment
            val barGraphFragment = BarGraphFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, barGraphFragment)
                .commit()

            currentFragment = barGraphFragment
        } else {
            super.onBackPressed()
        }
    }
}