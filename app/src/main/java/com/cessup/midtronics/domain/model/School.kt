package com.cessup.midtronics.domain.model

data class School(
    val id: Int = 0,
    val name: String,
    val studies: String,
    val dateStart: Long?,
    val dateEnd: Long?,
    val picture: String?
)