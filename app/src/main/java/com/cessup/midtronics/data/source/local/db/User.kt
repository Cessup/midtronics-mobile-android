package com.cessup.midtronics.data.source.local.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Represents a user.
 *
 * @property id unique identifier
 * @property email the email the user belongs to
 * @property phone the phone of the user for account
 * @property idDetails All information about this user
 */
@Entity(
    tableName = "users",
    foreignKeys = [
        ForeignKey(
            entity = UserDetails::class,
            parentColumns = ["id"],
            childColumns = ["idDetails"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val email: String,
    val phone: String,
    val idDetails: Int?
)