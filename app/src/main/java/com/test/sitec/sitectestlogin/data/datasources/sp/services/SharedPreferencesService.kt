package com.test.sitec.sitectestlogin.data.datasources.sp.services

import android.content.Context
import androidx.core.content.edit
import com.test.sitec.sitectestlogin.BuildConfig
import com.test.sitec.sitectestlogin.domain.common.App
import com.test.sitec.sitectestlogin.domain.common.SP_DEVICE_UUID

class SharedPreferencesService {
    val context: Context = App.instance

    val preferences =
        context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)

    // Commons
    var deviceUid: String?
        set(value) = preferences.edit {
            putString(SP_DEVICE_UUID, value)
            value?.let { putBoolean(SP_DEVICE_UUID, false) }
        }
        get() = preferences.getString(SP_DEVICE_UUID, "")


}