package com.adrian.commons.rx_interactor.domain.usecases

import com.adrian.commons.rx_interactor.domain.executor.PostExecutionThread
import com.adrian.commons.rx_interactor.domain.executor.ThreadExecutor
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class CompletableUseCase <Params>(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
): UseCase<Params, Completable>() {

    protected fun execute(params: Params? = null): Completable {
        return buildUseCase(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler())
    }
}