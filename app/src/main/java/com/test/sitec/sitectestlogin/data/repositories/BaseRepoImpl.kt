package com.test.sitec.sitectestlogin.data.repositories

import com.test.sitec.sitectestlogin.data.datasources.network.models.requests.SignInRequest
import com.test.sitec.sitectestlogin.data.datasources.network.models.responses.ErrorResponse
import com.test.sitec.sitectestlogin.data.datasources.network.models.responses.SignInResponse
import com.test.sitec.sitectestlogin.data.datasources.network.models.responses.UsersResponse
import com.test.sitec.sitectestlogin.data.datasources.network.services.BaseApiService
import com.test.sitec.sitectestlogin.domain.repositories.BaseRepo
import retrofit2.Response

class BaseRepoImpl: BaseRepo {
    private val networkApiDataSource = BaseApiService().getBaseApiService()

    override suspend fun getUsers(): Response<UsersResponse> {
        return networkApiDataSource.getUsers()
    }

    override suspend fun signIn(signInRequest: SignInRequest): Response<SignInResponse> {
        return networkApiDataSource.signIn(signInRequest.uid,
            signInRequest.pass,
            signInRequest.copyFromDevice,
            signInRequest.nfc)
    }
}