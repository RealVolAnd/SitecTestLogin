package com.test.sitec.sitectestlogin.presentation.ui.signin

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import com.test.sitec.sitectestlogin.presentation.models.User

class UserListLiveData  {
    companion object {
        private val usersList: MutableLiveData<ArrayList<User>> = MutableLiveData<ArrayList<User>>()
        private lateinit var sInstance: UserListLiveData

        fun getData() = usersList

        @MainThread
        fun get(): UserListLiveData {
            sInstance = if (::sInstance.isInitialized) sInstance else UserListLiveData()
            return sInstance
        }
    }
}