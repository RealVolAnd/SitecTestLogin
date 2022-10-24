package com.test.sitec.sitectestlogin.presentation.models

import com.google.gson.annotations.SerializedName

data class UsersList (
    val list:ArrayList<User>
        )

data class User (
    val testUser: String,
    val testUserUid: String,
    val testUserLang: String
){
    override fun toString(): String {
        return testUser
    }
}