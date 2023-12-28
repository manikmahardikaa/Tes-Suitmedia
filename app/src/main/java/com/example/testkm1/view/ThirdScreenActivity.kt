package com.example.testkm1.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testkm1.ViewModelFactory
import com.example.testkm1.databinding.ActivityThirdScreenBinding
import com.example.testkm1.view.adapter.LoadingStateAdapter
import com.example.testkm1.view.adapter.UserAdapter
import com.example.testkm1.view.viewmodel.ThirdScreenViewModel


class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding
    private val viewModel by viewModels<ThirdScreenViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager


        getUsers()

    }


    private fun getUsers(){
        val adapter = UserAdapter(this@ThirdScreenActivity)
        binding.recyclerView.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter{
                adapter.retry()
            }
        )

        viewModel.getUsersPaging.observe(this){user ->
            if(user != null){
                adapter.submitData(lifecycle, user)
                showLoading(false)
            }

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

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE

    }
}
