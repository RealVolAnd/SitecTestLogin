package com.test.sitec.sitectestlogin.presentation.ui.signin

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.sitec.sitectestlogin.common.App
import com.test.sitec.sitectestlogin.common.ERROR_GENERAL
import com.test.sitec.sitectestlogin.common.ERROR_NO_DATA
import com.test.sitec.sitectestlogin.common.ERROR_NO_NETWORK
import com.test.sitec.sitectestlogin.data.datasources.network.models.requests.SignInRequest
import com.test.sitec.sitectestlogin.domain.usecases.BaseUseCases
import com.test.sitec.sitectestlogin.presentation.mappers.UserListMapper
import com.test.sitec.sitectestlogin.presentation.ui.splash.SplashLiveData
import kotlinx.coroutines.*

class SignInViewModel : ViewModel(), LifecycleObserver {
    private val baseUseCases: BaseUseCases = BaseUseCases()
    private val sharedLiveData = UserListLiveData.getData()
    private val liveData: MutableLiveData<SignInLiveData> = MutableLiveData()
    private var job: Job? = null

    fun getUsersListLiveData() = sharedLiveData
    fun getLiveData() = liveData

    fun signIn(signInRequest: SignInRequest) {
        if(App.instance.isConnected) {
            job = CoroutineScope(Dispatchers.IO).launch {

                val response = baseUseCases.signIn(signInRequest)

                withContext(Dispatchers.Main) {
                    try {
                        if (response.isSuccessful) {
                            liveData.postValue(
                                SignInLiveData.Success(response.body()!!))
                        } else {
                            liveData.postValue(
                                SignInLiveData.Error(ERROR_NO_DATA)
                            )
                        }
                    } catch (e: Exception) {
                        liveData.postValue(
                            SignInLiveData.Error(ERROR_GENERAL)
                        )
                    }
                }
            }
        } else {
            liveData.postValue(
                SignInLiveData.Error(ERROR_NO_NETWORK)
            )
        }
    }

}