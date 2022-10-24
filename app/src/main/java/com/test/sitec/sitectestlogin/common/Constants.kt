package com.test.sitec.sitectestlogin.common

import com.test.sitec.sitectestlogin.R

//Commons
val DB_NAME = "test_db"
val UUID = "111111111111111"

//Network
val API_BASE_URL = "https://dev.sitec24.ru/UKA_TRADE/hs/MobileClient/${UUID}/"
val API_USER_NAME = "http"
val API_PASSWORD = "http"

//Log message types
val LOG_MESSAGE_TYPE_SUCCESS = 1
val LOG_MESSAGE_TYPE_ERROR = 2

//Local preferences keys
val SP_DEVICE_UUID = "device_uuid"

//Alert dialog types
val ALERT_DIALOG_TYPE_ERROR  =1
val ALERT_DIALOG_TYPE_WARN  = 2
val ALERT_DIALOG_TYPE_INFO  = 3

//Errors
val ERROR_GENERAL = App.instance.getString(R.string.s_w_w)
val ERROR_NO_NETWORK = App.instance.getString(R.string.no_network_error)
val ERROR_NO_DATA = App.instance.getString(R.string.server_return_empty_set)
val ERROR_LOGIN_FAILED = "Login failed"