package com.adrian.core.domain.usecases

import com.adrian.commons.rx_interactor.domain.executor.PostExecutionThread
import com.adrian.commons.rx_interactor.domain.executor.ThreadExecutor
import com.adrian.commons.rx_interactor.domain.usecases.SingleUseCase
import com.adrian.core.data.repository.LoginRepository
import com.adrian.core.domain.exceptions.GenericException
import com.adrian.core.models.dto.request.LoginRqtDto
import com.adrian.core.models.dto.response.LoginRsDto
import io.reactivex.rxjava3.core.Single

class LoginUseCase(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val loginRepository: LoginRepository
): SingleUseCase<LoginRqtDto, LoginRsDto>(threadExecutor, postExecutionThread) {
    override fun buildUseCase(params: LoginRqtDto?): Single<LoginRsDto> {
        return params?.let {
            loginRepository.makeLoginRequest(it)
        } ?: Single.error(GenericException())
    }
}