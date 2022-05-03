package com.adrian.core.domain.usecases

import com.adrian.commons.rx_interactor.domain.executor.PostExecutionThread
import com.adrian.commons.rx_interactor.domain.executor.ThreadExecutor
import com.adrian.commons.rx_interactor.domain.usecases.SingleUseCase
import com.adrian.core.data.repository.LoginRepository
import com.adrian.core.domain.exceptions.GenericException
import com.adrian.core.models.dto.request.RegisterUserRqDto
import com.adrian.core.models.dto.response.RegisterUserRsDto
import io.reactivex.rxjava3.core.Single

class RegisterUserUseCase(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val loginRepository: LoginRepository
): SingleUseCase<RegisterUserRqDto, RegisterUserRsDto>(threadExecutor, postExecutionThread)
{
    override fun buildUseCase(params: RegisterUserRqDto?): Single<RegisterUserRsDto> {
        return params?.let {
            loginRepository.registerUserRequest(it)
        } ?: Single.error(GenericException())
    }
}