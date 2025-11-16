package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Use a simple layout without view binding
        setContentView(R.layout.activity_main)
    }
}



