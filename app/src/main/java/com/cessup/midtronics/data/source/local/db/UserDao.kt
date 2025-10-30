package com.cessup.midtronics.data.source.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * User DAO is an interface about user table in the database.
 *
 * There are functions about control user data.
 *
 * @author
 *     Cessup
 * @since 1.0
 */
@Dao
interface UserDao {
    /**
     * This function insert a user in the user table.
     *
     * @property user unique identifier
     */
    @Insert suspend fun insert(user: User)
    /**
     * This function update a user in the user table.
     *
     * @property user unique identifier
     */
    @Update suspend fun update(user: User)
    /**
     * This function delete a user in the user table.
     *
     * @property user unique identifier
     */
    @Delete suspend fun delete(user: User)
    /**
     * This function insert a user in the user table.
     *
     * @property uid unique identifier of the user to help to find it in database
     *
     * @return [Boolean] this value is the result of the delete operation
     */
    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    fun getUserById(id: Int): Flow<User?>
    /**
     * This function insert a user in the user table.
     *
     * @property userEntity unique identifier
     *
     * @return [List<User>] Get All users in user table of database.
     */
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>

    /**
     * This function find a user details in the user details table.
     *
     * @property uid unique identifier of the user to help to find it in database
     *
     * @return [UserDetails] this value is the result of the delete operation
     */
    @Transaction
    @Query("SELECT * FROM userdetails WHERE id = :id")
    fun getUserDetails(id: Int): Flow<UserDetails?>
}