package com.alfanlukeyan.suitmediainterntest.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alfanlukeyan.suitmediainterntest.databinding.ActivitySecondScreenBinding

class SecondScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding
    private lateinit var nameProp: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get all data from intent
        val name = intent.getStringExtra("name").toString()
        val username = intent.getStringExtra("username").toString()

        // Display the name
        if (name.isNotEmpty()) {
            binding.tvName.text = name
        } else {
            binding.tvName.text = "John Doe"
        }

        // Display the username or hide it
        if (username.isNotEmpty()) {
            binding.tvUsername.visibility = View.INVISIBLE
            binding.tvSelected.visibility = View.VISIBLE
            binding.tvSelected.text = username
        } else {
            binding.tvUsername.visibility = View.VISIBLE
            binding.tvSelected.visibility = View.INVISIBLE
        }

        // Invoke the functions
        btnBackHandler()
        btnChooseUser()
    }

    /*
    * private fun btnBackHandler
    * -> Handles the back button click to navigate to the first screen.
    * */
    private fun btnBackHandler() {
        binding.btnBack.setOnClickListener {
            // Create intent and start activity
            val intent = Intent(this, FirstScreen::class.java)
            startActivity(intent)
        }
    }

    /*
    * private fun btnChooseUser
    * -> Handles the button click to navigate to the third screen to choose a user.
    * */
    private fun btnChooseUser() {
        binding.btnChoose.setOnClickListener {
            // Create intent and start activity
            val intent = Intent(this, ThirdScreen::class.java).also {
                it.putExtra("name", binding.tvName.text.toString())
                startActivity(it)
            }
        }
    }
}
