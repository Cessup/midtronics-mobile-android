package com.cessup.midtronics.data.source.local.db

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithDetails(
    @Embedded val user: User,

    @Relation(
        parentColumn = "idDetails",
        entityColumn = "id"
    )
    val details: UserDetails?
)