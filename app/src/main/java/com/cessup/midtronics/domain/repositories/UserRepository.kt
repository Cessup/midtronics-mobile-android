package com.cessup.midtronics.domain.repositories

import com.cessup.midtronics.domain.model.User
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
    suspend fun getUser(id: Int): Flow<User?>
}