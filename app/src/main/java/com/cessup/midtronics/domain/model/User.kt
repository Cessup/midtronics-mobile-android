package com.cessup.midtronics.domain.model

/**
 * Represents a user.
 *
 * @property id unique identifier
 * @property email the email the user belongs to
 * @property phone the phone of the user for account
 * @property idDetails unique identifier to their details
 */
data class User(
    val id: Int,
    val email: String,
    val phone: String,
    val idDetails: Int?
)