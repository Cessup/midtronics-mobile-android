package com.cessup.midtronics.data.source.local.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface WorkDao {
    @Query("SELECT * FROM work WHERE idUser = :userId")
    suspend fun getWorkByUser(userId: Int): List<Work>
}