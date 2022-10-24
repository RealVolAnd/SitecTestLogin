package com.test.sitec.sitectestlogin.common

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.sitec.sitectestlogin.data.datasources.db.services.TestDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        db = Room.databaseBuilder(this, TestDatabase::class.java, DB_NAME)
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            //.addMigrations(MIGRATION_1_2, MIGRATION_2_3)
            .build()
    }
    /*
    val MIGRATION_1_2 = object: Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE logitems ADD COLUMN guid INTEGER NOT NULL DEFAULT 0")
        }
      }
    */

    val isConnected: Boolean
        get() {
            val connectivityManager =
                this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = connectivityManager.activeNetworkInfo
            return netInfo != null && netInfo.isConnected
        }

    companion object {
        lateinit var instance: App
        lateinit var db: TestDatabase
    }
}