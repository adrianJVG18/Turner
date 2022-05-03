package com.adrian.commons.rx_interactor.domain.usecases

import io.reactivex.rxjava3.disposables.Disposable

abstract class UseCase <Params, R> {

    private var disposable: Disposable? = null

    protected abstract fun buildUseCase(params: Params? = null): R

    fun dispose() {
        disposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }
}