package com.adrian.commons.rx_interactor.data.job

import com.adrian.commons.rx_interactor.domain.executor.PostExecutionThread
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

class UIThread : PostExecutionThread {
    override fun getScheduler(): Scheduler = AndroidSchedulers.mainThread()
}