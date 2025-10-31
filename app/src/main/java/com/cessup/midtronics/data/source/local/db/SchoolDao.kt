package com.cessup.midtronics.data.source.local.db

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SchoolDao {
    @Query("SELECT * FROM school WHERE idUser = :userId")
    fun getSchoolsByUser(userId: Int): Flow<List<School>>
}