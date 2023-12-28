package com.example.testkm1.data.api

import com.example.testkm1.data.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): UserResponse
}

