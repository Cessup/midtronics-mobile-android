package com.cessup.midtronics

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cessup.midtronics.data.source.local.db.AppDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AppDatabaseAssetTest {

    private lateinit var db: AppDatabase
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()

        db = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "test_asset_db.db" // temporary DB name
        )
            .createFromAsset("database/original.db")
            .allowMainThreadQueries() // allowed for testing
            .build()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.close()
    }

    @Test
    fun database_loadsSuccessfullyFromAsset() {
        assertNotNull(db)

        assertNotNull(db.userDao())
        assertNotNull(db.schoolDao())
        assertNotNull(db.workDao())
    }

    @Test
    fun query_existingUserFromAsset() = runBlocking {
        val userFlow = db.userDao().getUserById(1)
        val user = userFlow.first()

        // Replace with expected values from your prebuilt asset
        assertNotNull("User with ID 1 should exist in asset DB", user)
        user?.let {
            println("User from asset: ${it.email} | ${it.phone}")
        }
    }
}
