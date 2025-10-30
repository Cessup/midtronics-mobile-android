package com.cessup.midtronics.data.source.local.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "school",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["idUser"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class School(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val studies: String,
    val dateStart: Long?,
    val dateEnd: Long?,
    val picture: String?,
    val idUser: Int
)


