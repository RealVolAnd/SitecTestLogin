package com.test.sitec.sitectestlogin.domain.common.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    fun getCurrentDateTimeString(): String {
        val df: DateFormat = SimpleDateFormat("HH:mm dd-MM-yyyy")
        return df.format(Date(System.currentTimeMillis()))
    }
}