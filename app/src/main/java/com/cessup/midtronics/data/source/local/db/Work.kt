package com.cessup.midtronics.data.source.local.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "work",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["idUser"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class Work(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val position: String,
    val company: String,
    val dateStart: Long?,
    val dateEnd: Long?,
    val description: String,
    val picture: String?,
    val idUser: Int
)