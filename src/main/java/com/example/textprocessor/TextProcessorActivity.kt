package com.example.textprocessor

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.textprocessor.R

class TextProcessorActivity : Activity() {

    private lateinit var textViewRetrievedData: TextView
    private lateinit var editTextInput: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonRetrieve: Button
    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        private const val PREFS_NAME = "TextProcessorPrefs"
        private const val KEY_SAVED_DATA = "saved_data"
        private const val KEY_DATA_LIST = "data_list"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_processor)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        // Initialize views
        textViewRetrievedData = findViewById(R.id.textViewRetrievedData)
        editTextInput = findViewById(R.id.editTextInput)
        buttonSave = findViewById(R.id.buttonSave)
        buttonRetrieve = findViewById(R.id.buttonRetrieve)

        // Retrieve and display data on startup
        retrieveAndDisplayData()

        // Set up button click listeners
        buttonSave.setOnClickListener {
            saveData()
        }

        buttonRetrieve.setOnClickListener {
            retrieveAndDisplayData()
        }
    }

    private fun retrieveAndDisplayData() {
        // Retrieve saved data from SharedPreferences
        val savedData = sharedPreferences.getString(KEY_SAVED_DATA, "")
        val dataList = sharedPreferences.getStringSet(KEY_DATA_LIST, mutableSetOf()) ?: mutableSetOf()

        // Build display text
        val displayText = StringBuilder()
        displayText.append("Retrieved Data:\n\n")
        
        if (savedData.isNullOrEmpty() && dataList.isEmpty()) {
            displayText.append("(No data found)")
        } else {
            if (!savedData.isNullOrEmpty()) {
                displayText.append("Last Saved: $savedData\n\n")
            }
            if (dataList.isNotEmpty()) {
                displayText.append("All Saved Items:\n")
                dataList.forEachIndexed { index, item ->
                    displayText.append("${index + 1}. $item\n")
                }
            }
        }

        // Display the retrieved data
        textViewRetrievedData.text = displayText.toString()
        
        // Show toast notification
        Toast.makeText(this, "Data retrieved successfully", Toast.LENGTH_SHORT).show()
    }

    private fun saveData() {
        val inputText = editTextInput.text.toString().trim()

        if (inputText.isEmpty()) {
            Toast.makeText(this, "Please enter some text", Toast.LENGTH_SHORT).show()
            return
        }

        // Save the current input as the last saved data
        sharedPreferences.edit().putString(KEY_SAVED_DATA, inputText).apply()

        // Add to the data list
        val dataList = sharedPreferences.getStringSet(KEY_DATA_LIST, mutableSetOf())?.toMutableSet() ?: mutableSetOf()
        dataList.add(inputText)
        sharedPreferences.edit().putStringSet(KEY_DATA_LIST, dataList).apply()

        // Clear the input field
        editTextInput.text.clear()

        // Refresh the display
        retrieveAndDisplayData()

        // Show success message
        Toast.makeText(this, "Data saved and returned successfully!", Toast.LENGTH_SHORT).show()
    }
}



