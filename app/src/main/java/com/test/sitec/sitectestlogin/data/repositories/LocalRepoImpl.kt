package com.test.sitec.sitectestlogin.data.repositories

import com.test.sitec.sitectestlogin.data.datasources.db.models.LogItem
import com.test.sitec.sitectestlogin.domain.common.App
import com.test.sitec.sitectestlogin.domain.repositories.LocalRepo
import io.reactivex.Observable

class LocalRepoImpl : LocalRepo {
    private val dbDataSource = App.db

    override fun getLog(): Observable<List<LogItem>> {
        return dbDataSource.logDao.getLog()
    }

    override suspend fun insertItem(logItem: LogItem) {
        dbDataSource.logDao.insert(logItem)
    }
}