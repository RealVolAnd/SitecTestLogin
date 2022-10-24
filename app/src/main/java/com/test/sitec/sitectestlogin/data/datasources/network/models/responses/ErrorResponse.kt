package com.test.sitec.sitectestlogin.data.datasources.network.models.responses

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status") val errorStatus: String,
    @SerializedName("message") val errorMassage: String,
    @SerializedName("data") val errorData: ArrayList<String>
)