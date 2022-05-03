package com.adrian.commons.rx_interactor.data.job

import com.adrian.commons.rx_interactor.domain.executor.PostExecutionThread
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class ComputationThread: PostExecutionThread {
    override fun getScheduler(): Scheduler = Schedulers.computation()
}