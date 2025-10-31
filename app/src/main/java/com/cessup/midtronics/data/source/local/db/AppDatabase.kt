package com.cessup.midtronics.data.source.local.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
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
@Database(
    entities = [User::class, UserDetails::class, School::class, Work::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun schoolDao(): SchoolDao
    abstract fun workDao(): WorkDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val assetManager = context.assets
                assetManager.open("database/original.db").use {
                    Log.d("DATABASE MIDTRONICS", "Asset exists (${it.available()} bytes)")
                }

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "midtronics.db"
                )
                    .createFromAsset("database/original.db")
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}

