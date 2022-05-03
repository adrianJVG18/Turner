package com.adrian.core.data.repository

import com.adrian.core.models.dto.request.LoginRqtDto
import com.adrian.core.models.dto.request.RegisterUserRqDto
import com.adrian.core.models.dto.response.LoginRsDto
import com.adrian.core.models.dto.response.RegisterUserRsDto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface LoginRepository {
    fun makeLoginRequest(user: LoginRqtDto): Single<LoginRsDto>
    fun registerUserRequest(user: RegisterUserRqDto): Single<RegisterUserRsDto>
    fun logOut(): Completable
}