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
            val lettersOri: Array<String> = word.split("").toTypedArray()
            val lettersReverse: Array<String> = lettersOri.reversedArray()
            val reversedWord = concatReversedLetter(lettersReverse)
            if (reversedWord == word) {
                Toast.makeText(this, "It's Palindrome", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No Palindrome", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun btnNextHandler() {
/*        binding.btnNext.setOnClickListener {
            if (binding.inputName.text.isEmpty()) {
                Toast.makeText(this, "Fill the name", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this@FirstScreen, SecondScreen::class.java).also {
                    val name = binding.inputName.text.toString()
                    it.putExtra("name", if (name != "") name else "John Doe")
                    it.putExtra("username", "")

                    startActivity(it)
                }
            }
        }*/
    }

    private fun concatReversedLetter(arr: Array<String>): String {
        var reversedWord = ""
        for (letter in arr) {
            reversedWord += letter
        }
        return reversedWord
    }
}
