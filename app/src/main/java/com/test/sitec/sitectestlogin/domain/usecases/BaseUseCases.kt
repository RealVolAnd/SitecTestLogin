package com.test.sitec.sitectestlogin.domain.usecases

import com.test.sitec.sitectestlogin.data.datasources.network.models.requests.SignInRequest
import com.test.sitec.sitectestlogin.data.datasources.network.models.responses.ErrorResponse
import com.test.sitec.sitectestlogin.data.datasources.network.models.responses.SignInResponse
import com.test.sitec.sitectestlogin.data.datasources.network.models.responses.UsersResponse
import com.test.sitec.sitectestlogin.data.repositories.BaseRepoImpl
import retrofit2.Response

class BaseUseCases {
    private val baseRepo = BaseRepoImpl()

    suspend fun getUsers(): Response<UsersResponse>{
        return baseRepo.getUsers()
    }

    suspend fun signIn(signInRequest: SignInRequest): Response<SignInResponse>{
        return baseRepo.signIn(signInRequest)
    }
}