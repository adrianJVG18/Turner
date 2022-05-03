package com.adrian.commons.rx_interactor.domain.usecases

import com.adrian.commons.rx_interactor.domain.executor.PostExecutionThread
import com.adrian.commons.rx_interactor.domain.executor.ThreadExecutor
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class SingleUseCase <Params, T>(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
): UseCase<Params, Single<T>>() {

    protected fun execute(params: Params? = null): Single<T> {
        return buildUseCase(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler())
    }
}