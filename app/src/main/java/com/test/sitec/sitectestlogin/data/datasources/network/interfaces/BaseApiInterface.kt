package com.test.sitec.sitectestlogin.data.datasources.network.interfaces

import com.test.sitec.sitectestlogin.data.datasources.network.models.requests.SignInRequest
import com.test.sitec.sitectestlogin.data.datasources.network.models.responses.ErrorResponse
import com.test.sitec.sitectestlogin.data.datasources.network.models.responses.SignInResponse
import com.test.sitec.sitectestlogin.data.datasources.network.models.responses.UsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface BaseApiInterface {

    //Users List
    @Headers("Accept: application/json")
    @GET("form/users")
    suspend fun getUsers(): Response<UsersResponse>

    //SignIn
    @Headers("Accept: application/json")
    @GET("authentication")
    suspend fun signIn(@Query("uid") uid: String,
                       @Query("pass") pass: String,
                       @Query("copyFromDevice") copyFromDevice: Boolean,
                       @Query("nfc ") nfc : String,): Response<SignInResponse>




}