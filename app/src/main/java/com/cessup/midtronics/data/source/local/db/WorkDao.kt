package com.cessup.midtronics.data.source.local.db

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkDao {
    @Query("SELECT * FROM work WHERE idUser = :userId")
    fun getWorkByUser(userId: Int): Flow<List<Work>>
}