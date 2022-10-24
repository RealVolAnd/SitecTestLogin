package com.test.sitec.sitectestlogin.data.datasources.db.interfaces

import androidx.room.*
import com.test.sitec.sitectestlogin.data.datasources.db.models.LogItem
import io.reactivex.Observable

@Dao
interface LogDao {
    @Query("SELECT * FROM logitems ORDER BY id DESC")
    fun getLog(): Observable<List<LogItem>>

    @Insert
    suspend fun insert(appLog: LogItem)

    @Update
    fun update(appLog: LogItem)

    @Delete
    fun delete(appLog: LogItem)
}