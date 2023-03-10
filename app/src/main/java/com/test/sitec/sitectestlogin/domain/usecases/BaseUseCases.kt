package com.test.sitec.sitectestlogin.domain.usecases

import com.test.sitec.sitectestlogin.data.datasources.network.models.responses.SignInResponse
import com.test.sitec.sitectestlogin.data.datasources.network.models.responses.UsersResponse
import com.test.sitec.sitectestlogin.data.repositories.BaseRepoImpl
import com.test.sitec.sitectestlogin.domain.mappers.BaseDataMapper
import com.test.sitec.sitectestlogin.presentation.models.RequestSignIn
import retrofit2.Response
import javax.inject.Inject

class BaseUseCases @Inject constructor() {
    private val baseRepo = BaseRepoImpl()

    suspend fun getUsers(): Response<UsersResponse> {
        return baseRepo.getUsers()
    }

    suspend fun signIn(signInRequest: RequestSignIn): Response<SignInResponse> {
        return baseRepo.signIn(
            BaseDataMapper().fromLocalSignInRequestToNetworkSignInRequest(
                signInRequest
            )
        )
    }
}