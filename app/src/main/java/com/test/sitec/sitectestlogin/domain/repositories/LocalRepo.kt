package com.test.sitec.sitectestlogin.domain.repositories

import com.test.sitec.sitectestlogin.data.datasources.db.models.LogItem
import io.reactivex.Observable

interface LocalRepo {
    fun getLog(): Observable<List<LogItem>>
    suspend fun insertItem(logItem: LogItem)
}