package com.test.sitec.sitectestlogin.presentation.ui.signin

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.sitec.sitectestlogin.common.*
import com.test.sitec.sitectestlogin.data.datasources.db.models.LogItem
import com.test.sitec.sitectestlogin.data.datasources.network.models.requests.SignInRequest
import com.test.sitec.sitectestlogin.domain.usecases.BaseUseCases
import com.test.sitec.sitectestlogin.domain.usecases.LocalUseCases
import com.test.sitec.sitectestlogin.presentation.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel(), LifecycleObserver {
    @Inject
    lateinit var baseUseCases: BaseUseCases

    @Inject
    lateinit var localUseCases: LocalUseCases

    @Inject
    lateinit var sharedLiveData: MutableLiveData<ArrayList<User>>
    private val liveData: MutableLiveData<SignInLiveData> = MutableLiveData()
    private var job: Job? = null

    fun getUsersListLiveData() = sharedLiveData
    fun getLiveData() = liveData

    fun signIn(signInRequest: SignInRequest) {
        if (App.instance.isConnected) {
            job = CoroutineScope(Dispatchers.IO).launch {

                val response = baseUseCases.signIn(signInRequest)

                withContext(Dispatchers.Main) {
                    try {
                        if (response.isSuccessful) {
                            liveData.postValue(
                                SignInLiveData.Success(response.body()!!)
                            )
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

    fun insertLogItem(logItem: LogItem) {
        if (App.instance.isConnected) {
            job = CoroutineScope(Dispatchers.IO).launch {

                val response = localUseCases.insertLogItem(logItem)

                withContext(Dispatchers.Main) {
                    try {
                        liveData.postValue(
                            SignInLiveData.SuccessInsertLogItem(RESULT_OK)
                        )
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