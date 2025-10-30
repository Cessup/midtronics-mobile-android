package com.cessup.midtronics.domain.model

/**
 * Represents a user details.
 *
 * @property id unique identifier
 * @property name the name of the user
 * @property lastname the lastname of the user
 * @property address the address of the user
 * @property gender the gender of the user
 * @property birthdate the birthday of the user
 * @property picture the picture of the user
 */
data class UserDetails(
    val id: Int = 0,
    val name: String,
    val lastname: String,
    val address: String,
    val gender: String,
    val birthdate: Long?,
    val picture: String?
)