package com.test.sitec.sitectestlogin.presentation.ui.log

import com.test.sitec.sitectestlogin.common.ERROR_NO_DATA
import com.test.sitec.sitectestlogin.data.datasources.db.models.LogItem
import com.test.sitec.sitectestlogin.domain.usecases.LocalUseCases
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class LogPresenter(private val view: LogContract.View): LogContract.Presenter {
    private val useCases: LocalUseCases = LocalUseCases()

    override fun getLog() {
        val disposable = useCases.getLog().subscribeOn(Schedulers.io())!!
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    view.onLogReceived(it)
                },
                onError = {
                    view.showErrorDialog(ERROR_NO_DATA)
                }
            )
    }
}