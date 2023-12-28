package com.example.testkm1.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.testkm1.databinding.ActivitySecondScreenBinding

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.buttonChoose.setOnClickListener { navigateToThirdScreen() }

        var name = intent.getStringExtra("name")
        binding.nameText.text = name

        var firstName = intent.getStringExtra("first_name")
        var lastName = intent.getStringExtra("last_name")

        if (firstName != null && lastName != null) {
            binding.selectedUserText.text = "$firstName $lastName"
        } else {
            binding.selectedUserText.text = "Selected User Name"
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToThirdScreen(){
        startActivity(Intent(this, ThirdScreenActivity::class.java))
    }


}