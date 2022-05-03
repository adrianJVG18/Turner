package com.adrian.commons.rx_interactor.data.job

import com.adrian.commons.rx_interactor.domain.executor.ThreadExecutor
import java.util.concurrent.Executors

class JobExecutor : ThreadExecutor {
    companion object {
        private const val MAXIMUM_POOL_SIZE = 10
    }

    private val threadPoolExecutor by lazy {
        Executors.newFixedThreadPool(MAXIMUM_POOL_SIZE)
    }

    override fun execute(command: Runnable?) {
        threadPoolExecutor.execute(command)
    }
}