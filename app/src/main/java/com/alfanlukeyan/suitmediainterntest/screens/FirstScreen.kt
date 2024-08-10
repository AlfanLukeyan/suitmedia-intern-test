package com.alfanlukeyan.suitmediainterntest.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alfanlukeyan.suitmediainterntest.databinding.ActivityFirstScreenBinding

class FirstScreen : AppCompatActivity() {

    private lateinit var binding: ActivityFirstScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnNextHandler()
        btnCheckPalindrome()
    }

    private fun btnCheckPalindrome() {
        binding.btnCheck.setOnClickListener {
            val word = binding.inputPalindrome.text.toString()
            if (word.isEmpty()) {
                Toast.makeText(this, "Input cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                // Normalize the input by removing spaces and converting to lowercase
                val normalizedWord = word.replace("\\s+".toRegex(), "").toLowerCase()
                val reversedWord = normalizedWord.reversed()
                if (reversedWord == normalizedWord) {
                    Toast.makeText(this, "It's a Palindrome", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "No Palindrome", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun btnNextHandler() {
        binding.btnNext.setOnClickListener {
            if (binding.inputName.text.isEmpty()) {
                Toast.makeText(this, "Fill the name", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this@FirstScreen, SecondScreen::class.java).also {
                    val name = binding.inputName.text.toString()
                    it.putExtra("name", if (name.isNotEmpty()) name else "John Doe")
                    it.putExtra("username", "")

                    startActivity(it)
                }
            }
        }
    }

    private fun concatReversedLetter(arr: Array<String>): String {
        var reversedWord = ""
        for (letter in arr) {
            reversedWord += letter
        }
        return reversedWord
    }
}
