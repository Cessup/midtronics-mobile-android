package com.cessup.midtronics.data.source.local.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface SchoolDao {
    @Query("SELECT * FROM school WHERE idUser = :userId")
    suspend fun getSchoolsByUser(userId: Int): List<School>
}