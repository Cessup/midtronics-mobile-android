package com.cessup.midtronics.domain.model

data class Work(
    val id: Int = 0,
    val position: String,
    val company: String,
    val dateStart: Long?,
    val dateEnd: Long?,
    val description: String,
    val picture: String?
)