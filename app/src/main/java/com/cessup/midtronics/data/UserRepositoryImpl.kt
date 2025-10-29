package com.cessup.midtronics.data

import com.cessup.midtronics.domain.model.User
import com.cessup.midtronics.domain.model.UserDetails
import com.cessup.midtronics.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl : UserRepository {
    override suspend fun authenticate(
        email: String,
        password: String
    ): Flow<Result<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertUser(user: User): Flow<Result<Any>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(id: String): Flow<Result<Any>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserDetails(userDetails: UserDetails): Flow<Result<Any>> {
        TODO("Not yet implemented")
    }

    override suspend fun updatePassword(
        email: String,
        password: String
    ): Flow<Result<Any>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(id: String): Flow<Result<Any>> {
        TODO("Not yet implemented")
    }
}