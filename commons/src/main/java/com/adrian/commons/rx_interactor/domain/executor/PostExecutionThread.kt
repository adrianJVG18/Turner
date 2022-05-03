package com.adrian.commons.rx_interactor.domain.executor

import io.reactivex.rxjava3.core.Scheduler

interface PostExecutionThread {
    fun getScheduler(): Scheduler
}