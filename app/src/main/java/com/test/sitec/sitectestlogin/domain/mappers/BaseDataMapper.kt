package com.test.sitec.sitectestlogin.domain.mappers

import com.test.sitec.sitectestlogin.data.datasources.network.models.requests.SignInRequest
import com.test.sitec.sitectestlogin.data.datasources.network.models.responses.UsersResponse
import com.test.sitec.sitectestlogin.presentation.models.RequestSignIn
import com.test.sitec.sitectestlogin.presentation.models.User
import com.test.sitec.sitectestlogin.presentation.models.UsersList

class BaseDataMapper {
    fun fromServerUsersResponseToUsersList(usersResponse: UsersResponse): UsersList {
        val tmpArray: ArrayList<User> = ArrayList()
        usersResponse.users.listUsers.forEach {
            tmpArray.add(
                User(
                    it.testUser,
                    it.testUserUid,
                    it.testUserLang
                )
            )
        }
        return UsersList(tmpArray)
    }

    fun fromLocalSignInRequestToNetworkSignInRequest(localRequest: RequestSignIn) =
        SignInRequest(
            localRequest.uid,
            localRequest.pass,
            localRequest.copyFromDevice,
            localRequest.nfc
        )

}