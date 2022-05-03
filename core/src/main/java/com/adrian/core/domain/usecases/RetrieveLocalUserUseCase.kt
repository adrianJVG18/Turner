package com.adrian.core.domain.usecases

import com.adrian.commons.rx_interactor.domain.executor.PostExecutionThread
import com.adrian.commons.rx_interactor.domain.executor.ThreadExecutor
import com.adrian.commons.rx_interactor.domain.usecases.SingleUseCase
import com.adrian.core.data.framework.shared_preferences.EncryptedSharedPreferencesRepository
import com.adrian.core.domain.contants.Preferences.USER_CREDENTIALS
import com.adrian.core.models.dto.response.RetrieveLocalUserRsDto
import com.adrian.core.models.entity.LocalUser
import io.reactivex.rxjava3.core.Single

class RetrieveLocalUserUseCase(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val sharedPreferencesRepository: EncryptedSharedPreferencesRepository
) : SingleUseCase<Void, RetrieveLocalUserRsDto>(threadExecutor, postExecutionThread) {

    override fun buildUseCase(params: Void?): Single<RetrieveLocalUserRsDto> {
        return Single.create { emitter ->
            sharedPreferencesRepository.get(USER_CREDENTIALS)
                .map { jsonString ->
                    if (jsonString.isNotEmpty()) {
                        LocalUser.fromJson(jsonString)
                    } else {
                        LocalUser()
                    }
                }
                .subscribe {
                    emitter.onSuccess(
                        RetrieveLocalUserRsDto(it.email!!, it.pass!!)
                    )
                }
        }
    }
}