package com.test.sitec.sitectestlogin.data.datasources.db.services

import androidx.room.RoomDatabase
import com.test.sitec.sitectestlogin.data.datasources.db.interfaces.LogDao
import com.test.sitec.sitectestlogin.data.datasources.db.models.LogItem

@androidx.room.Database(
    entities = [LogItem::class],
    version = 1,
    exportSchema = false
)
abstract class TestDatabase : RoomDatabase() {
    abstract val logDao: LogDao

    companion object {

        private var instance: TestDatabase? = null

        fun getInstance() = instance
            ?: throw RuntimeException("Database has not been created. Please call create(context)")
    }
}