package com.cessup.midtronics.domain.repositories

import com.cessup.midtronics.domain.model.School
import com.cessup.midtronics.domain.model.User
import com.cessup.midtronics.domain.model.Work
import kotlinx.coroutines.flow.Flow

/**
 * User Repository have every data about the users.
 *
 * This class is a interface with all functions about user information
 * There are actions that the user can perform
 *
 * @author
 *     Cessup
 * @since 1.0
 */
interface UserRepository {
    /**
     * This function return information about user
     *
     * @param id the user information from the services is here
     * @return a user
     */
    fun getUser(id: Int): Flow<Result<User?>>
    /**
     * This function return url of image to user
     *
     * @return a string
     */
    fun getUserPicture(): String
    /**
     * This function return a list of Schools
     *
     * @return a string
     */
    fun getSchoolsListByIdUser(idUser: Int) : Flow<Result<List<School>?>>
    /**
     * This function return a list of Works
     *
     * @return a string
     */
    fun getWorkListByIdUser(idUser: Int) : Flow<Result<List<Work>?>>
}