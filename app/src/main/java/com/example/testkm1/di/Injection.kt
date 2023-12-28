package com.example.testkm1.di

import android.content.Context
import com.example.testkm1.data.api.ApiConfig
import com.example.testkm1.data.local.UserDatabase
import com.example.testkm1.data.repository.Repository

object Injection {
    fun provideRepository(context: Context): Repository {
        val apiService = ApiConfig.getApiService()
        val database = UserDatabase.getDatabase(context)
        return Repository.getInstance(apiService, database)
    }
}