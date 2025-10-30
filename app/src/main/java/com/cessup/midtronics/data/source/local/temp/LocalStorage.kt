package com.cessup.midtronics.data.source.local.temp

/**
 * LocalStorage is an interface about save some values in a Local Storage
 *
 * There are function to help to save the values
 *
 * @author
 *     Cessup
 * @since 1.0
 */
interface LocalStorage {
    /**
     * This function save a user in the user table.
     *
     * @param imgFromUrl unique identifier about session from server.
     */
    fun saveUserPictureFromUrl(imgFromUrl: String?)
    /**
     * This function give a user in the user table.
     *
     * @return String that is the token about session from server.
     */
    fun readUserPictureFromUrl(): String
}