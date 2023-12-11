package com.example.weatherapp.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R

class LocationSelectionFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_location_selection, container, false)

        val zipcodeEditText : EditText = view.findViewById(R.id.zipcodeEditText)
        val enterButton : Button = view.findViewById(R.id.enterButton)

        enterButton.setOnClickListener {

            val zipcode : String = zipcodeEditText.text.toString()

            if(zipcode.length != 5){
                Toast.makeText(requireContext(), "Enter a valid zipcode number", Toast.LENGTH_SHORT).show()
            }
            else{
                findNavController().navigateUp()

            }

        }

        return view
    }


    }
