package com.test.sitec.sitectestlogin.domain.repositories

import com.test.sitec.sitectestlogin.data.datasources.db.models.LogItem
import com.test.sitec.sitectestlogin.data.datasources.network.models.requests.SignInRequest
import com.test.sitec.sitectestlogin.data.datasources.network.models.responses.SignInResponse
import com.test.sitec.sitectestlogin.data.datasources.network.models.responses.UsersResponse
import io.reactivex.Observable
import retrofit2.Response

interface LocalRepo {
    suspend fun getLog(): Observable<List<LogItem>>
    suspend fun insertItem(logItem: LogItem)
}