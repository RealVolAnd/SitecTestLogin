package com.test.sitec.sitectestlogin.data.datasources.network.models.responses

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("Code") val code: Int
)