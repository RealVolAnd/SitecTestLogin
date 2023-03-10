package com.test.sitec.sitectestlogin.domain.mappers

import com.test.sitec.sitectestlogin.data.datasources.db.models.LogItem
import com.test.sitec.sitectestlogin.presentation.models.ItemToLog

class LocalDataMapper {
    fun fromLocalItemToLogToServerLogItem(localItem: ItemToLog) =
        LogItem(
            localItem.id,
            localItem.timestamp,
            localItem.logMessageType,
            localItem.logMessage
        )
}