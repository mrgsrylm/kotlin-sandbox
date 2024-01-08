package io.github.mrgsrylm.composeshop.data.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShopDatabaseTest {
    private lateinit var database: ShopDatabase
    private lateinit var dao: ShopDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, ShopDatabase::class.java).build()
        dao = database.dao
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun testDatabaseCreation() {
        assertThat(database).isNotNull()
    }

    @Test
    fun getDao() {
        assertThat(database.dao).isNotNull()
    }
}