package com.test.sitec.sitectestlogin.presentation.ui.splash

import com.test.sitec.sitectestlogin.presentation.models.UsersList

sealed class SplashLiveData {
    object Loading : SplashLiveData()
    data class Success(val usersList: UsersList) : SplashLiveData()
    data class Error(val error: String) : SplashLiveData()
}