package com.test.sitec.sitectestlogin.data.datasources.network.models.responses

import com.google.gson.annotations.SerializedName


data class UsersResponse(
    @SerializedName("Users") val users: TestUsersList
)

data class TestUsersList(
    @SerializedName("ListUsers") val listUsers: Array<TestUser>,
    @SerializedName("CurrentUid") val currentUid: String
)

data class TestUser(
@SerializedName("User") val testUser: String,
@SerializedName("Uid") val testUserUid: String,
@SerializedName("Language") val testUserLang: String
)
