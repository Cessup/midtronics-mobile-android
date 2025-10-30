package com.cessup.midtronics.data

import com.cessup.midtronics.data.source.local.db.AppDatabase
import com.cessup.midtronics.data.source.local.temp.LocalStorage
import com.cessup.midtronics.domain.model.User
import com.cessup.midtronics.domain.model.UserDetails
import com.cessup.midtronics.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(val database: AppDatabase, val localStorage: LocalStorage): UserRepository {

    override suspend fun getUser(id: Int): Flow<User?> =
        database.userDao().getUserById(id).map { entity ->
            entity?.let {
                User(
                    id = it.id,
                    email = it.email,
                    phone = it.phone,
                    idDetails = it.idDetails
                )
            }
        }

    override suspend fun getUserDetails(idUser: Int): Flow<UserDetails?> =
        database.userDao().getUserDetails(idUser).map { entity->
            entity?.let {
                localStorage.saveUserPictureFromUrl(it.picture)
                UserDetails(
                    id = it.id,
                    name = it.name,
                    lastname = it.lastname,
                    address = it.address,
                    gender = it.gender,
                    birthdate = it.birthdate,
                    picture = it.picture
                )
            }
        }

    override fun getUserPicture(): String = localStorage.readUserPictureFromUrl()
}