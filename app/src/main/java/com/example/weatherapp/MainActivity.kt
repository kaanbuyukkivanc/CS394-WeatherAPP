package com.example.weatherapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val zipcodeEditText : EditText = findViewById(R.id.zipcodeEditText)
        val enterButton : Button = findViewById(R.id.enterButton)

        enterButton.setOnClickListener {

            val zipcode : String = zipcodeEditText.text.toString()

            if(zipcode.length != 5){
                Toast.makeText(this, "Enter a valid zipcode number", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, zipcode, Toast.LENGTH_SHORT).show()
            }

        }


    }

    override fun onStart(){
        super.onStart()
    }

    override fun onResume(){
        super.onResume()
    }

    override fun onPause(){
        super.onPause()
    }

    override fun onStop(){
        super.onStop()
    }

    override fun onDestroy(){
        super.onDestroy()
    }


}

