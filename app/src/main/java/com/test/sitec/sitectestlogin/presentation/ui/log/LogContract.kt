package com.test.sitec.sitectestlogin.presentation.ui.log

import com.test.sitec.sitectestlogin.data.datasources.db.models.LogItem

class LogContract {
    interface View {
        fun onLogReceived(logItemsList: List<LogItem>)
        fun showErrorDialog(message: String)
    }

    interface Presenter {
        fun getLog()
    }
}