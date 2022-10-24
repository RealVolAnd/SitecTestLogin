package com.test.sitec.sitectestlogin.domain.usecases

import com.test.sitec.sitectestlogin.data.datasources.db.models.LogItem
import com.test.sitec.sitectestlogin.data.repositories.LocalRepoImpl
import io.reactivex.Observable
import javax.inject.Inject

class LocalUseCases @Inject constructor() {
    private val localRepo = LocalRepoImpl()

    fun getLog(): Observable<List<LogItem>> {
        return localRepo.getLog()
    }

    suspend fun insertLogItem(logItem: LogItem) {
        localRepo.insertItem(logItem)
    }
}