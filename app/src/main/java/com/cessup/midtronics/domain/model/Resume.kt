package com.cessup.midtronics.domain.model

/**
 * Represents a resume from user.
 *
 * @property user is the user with all information
 * @property schools is a list of schools of the user
 * @property works is a list of work of the user
 */
data class Resume(
    val user: User,
    val schools: List<School> ,
    val works: List<Work> 
)