package com.cessup.midtronics.data.source.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a user.
 *
 * @property uid unique identifier
 * @property name the name of the user
 * @property lastName the lastname of the user
 * @property gender the gender of the user
 * @property birthdate the birthday of the user
 */
@Entity(tableName = "userdetails")
data class UserDetails(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val lastname: String,
    val address: String,
    val gender: String,
    val birthdate: Long?,
    val picture: String?
)