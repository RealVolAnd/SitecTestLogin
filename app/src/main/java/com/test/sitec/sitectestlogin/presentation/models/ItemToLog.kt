package com.test.sitec.sitectestlogin.presentation.models

data class ItemToLog(
    val id: Long,
    val timestamp: String,
    val logMessageType: Int,
    val logMessage: String
)