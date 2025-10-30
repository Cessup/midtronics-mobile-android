package com.cessup.midtronics.data

import com.cessup.midtronics.data.source.local.db.AppDatabase
import com.cessup.midtronics.domain.model.User
import com.cessup.midtronics.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(val db: AppDatabase): UserRepository {

    override suspend fun getUser(id: Int): Flow<User?> =
        db.userDao().getUserById(id).map { entity ->
            entity?.let {
                User(
                    id = it.id,
                    email = it.email,
                    phone = it.phone,
                    idDetails = it.idDetails
                )
            }
        }

}