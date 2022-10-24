package com.test.sitec.sitectestlogin.presentation.ui.splash

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.sitec.sitectestlogin.common.App
import com.test.sitec.sitectestlogin.common.ERROR_GENERAL
import com.test.sitec.sitectestlogin.common.ERROR_NO_DATA
import com.test.sitec.sitectestlogin.common.ERROR_NO_NETWORK
import com.test.sitec.sitectestlogin.domain.usecases.BaseUseCases
import com.test.sitec.sitectestlogin.presentation.mappers.UserListMapper
import com.test.sitec.sitectestlogin.presentation.ui.signin.UserListLiveData
import kotlinx.coroutines.*

class SplashViewModel : ViewModel(), LifecycleObserver {
    private val baseUseCases: BaseUseCases = BaseUseCases()
    private val liveData: MutableLiveData<SplashLiveData> = MutableLiveData()
    private val sharedLiveData = UserListLiveData.getData()
    private var job: Job? = null

    fun getLiveData() = liveData

    fun getUsers() {
        if(App.instance.isConnected) {
            job = CoroutineScope(Dispatchers.IO).launch {

                val response = baseUseCases.getUsers()

                withContext(Dispatchers.Main) {
                    try {
                        if (response.isSuccessful) {
                            liveData.postValue(
                                SplashLiveData.Success(UserListMapper()
                                    .fromServerUsersResponseToUsersList(response.body()!!)))
                            sharedLiveData.postValue(UserListMapper()
                                .fromServerUsersResponseToUsersList(response.body()!!).list)
                        } else {
                            liveData.postValue(
                                SplashLiveData.Error(ERROR_NO_DATA)
                            )
                        }
                    } catch (e: Exception) {
                        liveData.postValue(
                            SplashLiveData.Error(ERROR_GENERAL)
                        )
                    }
                }
            }
        } else {
            liveData.postValue(
                SplashLiveData.Error(ERROR_NO_NETWORK)
            )
        }
    }



}