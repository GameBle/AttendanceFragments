package com.example.attendance

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create an instance of the BarGraphFragment
        val barGraphFragment = BarGraphFragment()

        // Add the BarGraphFragment to the activity
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, barGraphFragment)
            .commit()
    }
}