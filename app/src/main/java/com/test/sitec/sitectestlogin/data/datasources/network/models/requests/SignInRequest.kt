package com.test.sitec.sitectestlogin.data.datasources.network.models.requests

import com.google.gson.annotations.SerializedName

data class SignInRequest (
    @SerializedName("uid") val uid: String,
    @SerializedName("pass") val pass: String,
    @SerializedName("copyFromDevice") val copyFromDevice: Boolean,
    @SerializedName("nfc") val nfc: String = ""
)