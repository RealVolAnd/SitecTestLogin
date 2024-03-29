package com.test.sitec.sitectestlogin.presentation.di

import androidx.lifecycle.MutableLiveData
import com.test.sitec.sitectestlogin.presentation.models.User
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UserListLiveDataModule {
    @Provides
    fun provideUserListLiveData(): MutableLiveData<ArrayList<User>> {
        return UserListLiveData.getData()
    }
}
