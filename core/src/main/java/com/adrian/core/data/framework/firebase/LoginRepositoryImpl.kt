package com.adrian.core.data.framework.firebase

import com.adrian.core.data.repository.LoginRepository
import com.adrian.core.domain.exceptions.ApiException
import com.adrian.core.models.dto.request.LoginRqtDto
import com.adrian.core.models.dto.request.RegisterUserRqDto
import com.adrian.core.models.dto.response.LoginRsDto
import com.adrian.core.models.dto.response.RegisterUserRsDto
import com.google.firebase.auth.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class LoginRepositoryImpl: LoginRepository {

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun makeLoginRequest(user: LoginRqtDto): Single<LoginRsDto> {
        return Single.create { request ->
            auth.signInWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful && auth.currentUser != null) {
                        request.onSuccess(LoginRsDto(auth.currentUser!!.uid, user.email))
                    }
                }.addOnFailureListener { error ->
                    when (error) {
                        is FirebaseAuthInvalidCredentialsException -> {
                            request.onError(ApiException.WrongCredentials(error))
                        }
                        is FirebaseAuthInvalidUserException -> {
                            request.onError(ApiException.InvalidUser(error))
                        }
                        else -> {
                            request.onError(ApiException.FailedRequestException(error))
                        }
                    }
                }
        }
    }

    override fun registerUserRequest(user: RegisterUserRqDto): Single<RegisterUserRsDto> {
        return Single.create { request ->
            auth.createUserWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val responseDto = RegisterUserRsDto(auth.currentUser!!.uid)
                        request.onSuccess(responseDto)
                    }
                }.addOnFailureListener { error ->
                    when (error) {
                        is FirebaseAuthUserCollisionException -> {
                            request.onError(ApiException.UserAlreadyExistsException(error))
                        }
                        is FirebaseAuthWeakPasswordException -> {
                            request.onError(ApiException.WeakPasswordException(error))
                        }
                        else -> {
                            request.onError(ApiException.FailedRequestException(error))
                        }
                    }
                }
        }
    }

    override fun logOut(): Completable {
        return Completable.create { request ->
            auth.signOut()
            request.onComplete()
        }
    }
}