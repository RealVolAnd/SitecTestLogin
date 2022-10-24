package com.test.sitec.sitectestlogin.data.datasources.db.interfaces

import androidx.room.*
import com.test.sitec.sitectestlogin.data.datasources.db.models.LogItem
import io.reactivex.Observable

@Dao
interface LogDao {
    @Query("SELECT * FROM logitems")
    suspend fun getLog(): Observable<List<LogItem>>

    @Insert
    suspend fun insert(appLog: LogItem)

    @Update
    suspend fun update(appLog: LogItem)

    @Delete
    suspend fun delete(appLog: LogItem)
}