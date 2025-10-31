package com.cessup.midtronics.data

import com.cessup.midtronics.data.source.local.db.AppDatabase
import com.cessup.midtronics.data.source.local.temp.LocalStorage
import com.cessup.midtronics.domain.model.School
import com.cessup.midtronics.domain.model.User
import com.cessup.midtronics.domain.model.UserDetails
import com.cessup.midtronics.domain.model.Work
import com.cessup.midtronics.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(val database: AppDatabase, val localStorage: LocalStorage): UserRepository {

    override fun getUser(id: Int): Flow<Result<User?>> =
        database.userDao().getUserWithDetailsById(id)
            .map { userWithDetails ->
                if (userWithDetails?.user != null && userWithDetails.details != null) {
                    localStorage.saveUserPictureFromUrl(userWithDetails.details.picture)

                    val user = User(
                        id = userWithDetails.user.id,
                        email = userWithDetails.user.email,
                        phone = userWithDetails.user.phone,
                        details = UserDetails(
                            userWithDetails.details.id,
                            userWithDetails.details.name,
                            userWithDetails.details.lastname,
                            userWithDetails.details.address,
                            userWithDetails.details.gender,
                            userWithDetails.details.birthdate,
                            userWithDetails.details.picture
                        )
                    )
                    Result.success(user)
                } else {
                    Result.failure(NullPointerException("User or details are null"))
                }
            }
            .catch { e ->
                emit(Result.failure(e))
            }


    override fun getUserPicture(): String = localStorage.readUserPictureFromUrl()

    override fun getSchoolsListByIdUser(idUser: Int): Flow<Result<List<School>?>> =
        database.schoolDao().getSchoolsByUser(idUser)
            .map { schools ->
                val list = schools.map { school ->
                    School(
                        school.id,
                        school.name,
                        school.studies,
                        school.dateStart,
                        school.dateEnd,
                        school.picture
                    )
                }
                Result.success(list)
            }
            .catch { e ->
                emit(Result.failure(e))
            }


    override fun getWorkListByIdUser(idUser: Int): Flow<Result<List<Work>?>> =
        database.workDao().getWorkByUser(idUser)
            .map { works ->
                val list = works.map { work ->
                    Work(
                        work.id,
                        work.position,
                        work.company,
                        work.dateStart,
                        work.dateEnd,
                        work.description,
                        work.picture
                    )
                }
                Result.success(list)
            }
            .catch { e ->
                emit(Result.failure(e))
            }

}