package com.test.sitec.sitectestlogin.domain.repositories

import com.test.sitec.sitectestlogin.data.datasources.network.models.requests.SignInRequest
import com.test.sitec.sitectestlogin.data.datasources.network.models.responses.SignInResponse
import com.test.sitec.sitectestlogin.data.datasources.network.models.responses.UsersResponse
import retrofit2.Response

interface BaseRepo {
    suspend fun getUsers(): Response<UsersResponse>
    suspend fun signIn(signInRequest: SignInRequest): Response<SignInResponse>
}