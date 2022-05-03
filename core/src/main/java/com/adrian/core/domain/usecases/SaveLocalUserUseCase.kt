package com.adrian.core.domain.usecases

import com.adrian.commons.rx_interactor.domain.executor.PostExecutionThread
import com.adrian.commons.rx_interactor.domain.executor.ThreadExecutor
import com.adrian.commons.rx_interactor.domain.usecases.CompletableUseCase
import com.adrian.core.data.framework.shared_preferences.EncryptedSharedPreferencesRepository
import com.adrian.core.domain.contants.Preferences.USER_CREDENTIALS
import com.adrian.core.domain.exceptions.GenericException
import com.adrian.core.models.dto.request.SaveLocalUserRqDto
import com.adrian.core.models.entity.LocalUser
import io.reactivex.rxjava3.core.Completable

class SaveLocalUserUseCase(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val sharedPreferenceRepository: EncryptedSharedPreferencesRepository
) : CompletableUseCase<SaveLocalUserRqDto>(threadExecutor, postExecutionThread) {

    override fun buildUseCase(params: SaveLocalUserRqDto?): Completable {
        params?.let {
            return Completable.create { emitter ->
                val localUser = LocalUser(params.email, params.pass)
                sharedPreferenceRepository.put(USER_CREDENTIALS, localUser.toJson())
                    .subscribe {
                        emitter.onComplete()
                    }
            }
        } ?: throw GenericException()
    }
}