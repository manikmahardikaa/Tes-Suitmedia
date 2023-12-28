package com.example.testkm1.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.testkm1.data.UserRemoteMediator
import com.example.testkm1.data.api.ApiConfig
import com.example.testkm1.data.api.ApiService
import com.example.testkm1.data.local.UserDatabase
import com.example.testkm1.data.response.DataItem

class Repository(private val apiService: ApiService, private val userDatabase: UserDatabase) {

    fun getUsersPaging(): LiveData<PagingData<DataItem>>{
        val response = ApiConfig.getApiService()
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = UserRemoteMediator(userDatabase, response),
            pagingSourceFactory = {
                userDatabase.userDao().getAllStory()
            }
        ).liveData
    }

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            apiService: ApiService,
            userDatabase: UserDatabase
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(apiService, userDatabase)
            }.also { instance = it }
    }
}
