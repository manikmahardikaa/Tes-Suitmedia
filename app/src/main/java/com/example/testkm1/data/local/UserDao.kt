package com.example.testkm1.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testkm1.data.response.DataItem

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStory(story: List<DataItem>)

    @Query("SELECT * FROM user")
    fun getAllStory(): PagingSource<Int, DataItem>

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}