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
import com.test.sitec.sitectestlogin.presentation.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel(), LifecycleObserver {
    @Inject
    lateinit var baseUseCases: BaseUseCases
    private val liveData: MutableLiveData<SplashLiveData> = MutableLiveData<SplashLiveData>()

    @Inject
    lateinit var sharedLiveData: MutableLiveData<ArrayList<User>>
    private var job: Job? = null

    fun getLiveData() = liveData

    fun getUsers() {
        if (App.instance.isConnected) {
            job = CoroutineScope(Dispatchers.IO).launch {

                val response = baseUseCases.getUsers()

                withContext(Dispatchers.Main) {
                    try {
                        if (response.isSuccessful) {
                            liveData.postValue(
                                SplashLiveData.Success(
                                    UserListMapper()
                                        .fromServerUsersResponseToUsersList(response.body()!!)
                                )
                            )
                            sharedLiveData.postValue(
                                UserListMapper()
                                    .fromServerUsersResponseToUsersList(response.body()!!).list
                            )
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