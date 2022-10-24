package com.test.sitec.sitectestlogin.presentation.ui.signin

import com.test.sitec.sitectestlogin.data.datasources.network.models.responses.SignInResponse

sealed class SignInLiveData {
    object Loading : SignInLiveData()
    data class Success(val response: SignInResponse) : SignInLiveData()
    data class SuccessInsertLogItem(val result: Int) : SignInLiveData()
    data class Error(val error: String) : SignInLiveData()
}