package com.cessup.midtronics.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * AppDatabase is an interface about database configuration in this case is room by SQLite
 *
 * There are configurations for this framework
 *
 * @author
 *     Cessup
 * @since 1.0
 */
@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}