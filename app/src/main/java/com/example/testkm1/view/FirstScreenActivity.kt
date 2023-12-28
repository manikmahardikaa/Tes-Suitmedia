package com.example.testkm1.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.testkm1.databinding.ActivityFirstScreenBinding

class FirstScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.checkButton.setOnClickListener { checkPalindrome() }
        binding.nextButton.setOnClickListener { navigateToSecondScreen() }
    }

    private fun checkPalindrome() {
        val sentence = binding.sentenceEditText.text.toString().trim()

        if (sentence.isEmpty()) {
            showToast("Please enter a sentence.")
            return
        }

        val result = isPalindrome(sentence)

        val message = if (result) "Palindrome" else "Not Palindrome"

        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Palindrome Check")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        alertDialog.show()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    private fun isPalindrome(sentence: String): Boolean {
        val cleanedSentence = sentence.replace("\\s".toRegex(), "").toLowerCase()
        return cleanedSentence == cleanedSentence.reversed()
    }

    private fun navigateToSecondScreen(){
        val name = binding.nameEditText.text.toString()

        if (name.isEmpty()) {
            showToast("Please enter a name.")
            return
        }

        val intent = Intent(this, SecondScreenActivity::class.java)
        intent.putExtra("name", name)
        startActivity(intent)
    }

}
