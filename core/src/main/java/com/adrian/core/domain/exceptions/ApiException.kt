package com.adrian.core.domain.exceptions

sealed class ApiException(t: Throwable): kotlin.Exception() {

    class InvalidUser(t: Throwable): ApiException(t)

    class WrongCredentials(t: Throwable): ApiException(t)

    class FailedRequestException(t: Throwable): ApiException(t)

    class UserAlreadyExistsException(t: Throwable): ApiException(t)

    class WeakPasswordException(t: Throwable): ApiException(t)
}
