package com.test.sitec.sitectestlogin.presentation.mappers

import com.test.sitec.sitectestlogin.data.datasources.network.models.responses.UsersResponse
import com.test.sitec.sitectestlogin.presentation.models.User
import com.test.sitec.sitectestlogin.presentation.models.UsersList

class UserListMapper {
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
}