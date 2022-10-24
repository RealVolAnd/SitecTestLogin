package com.test.sitec.sitectestlogin.data.datasources.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "logitems",
    indices = [androidx.room.Index(value = ["timestamp"], unique = true)])
data class LogItem (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "timestamp")
    val timestamp: Long,
    @ColumnInfo(name = "log_message_type")
    val logMessageType: Int,
    @ColumnInfo(name = "log_message")
    val logMessage: String
): Serializable