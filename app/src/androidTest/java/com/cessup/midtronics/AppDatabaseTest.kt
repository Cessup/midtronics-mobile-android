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
class AppDatabaseTest {

    private lateinit var db: AppDatabase
    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        )
            .allowMainThreadQueries() // only for testing
            .build()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.close()
    }

    @Test
    fun database_isCreatedSuccessfully() {
        assertNotNull(db)
        assertNotNull(db.userDao())
        assertNotNull(db.schoolDao())
        assertNotNull(db.workDao())
    }

    @Test
    fun ReadUser() = runBlocking {
        val result = db.userDao().getUserWithDetailsById(1).first()

        assertNotNull(result)
        assertEquals("Cesar Ivan", result?.details?.name)
        assertEquals("cessupx@gmail.com", result?.user?.email)
    }
}
