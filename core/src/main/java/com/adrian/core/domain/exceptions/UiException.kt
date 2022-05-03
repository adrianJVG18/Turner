package com.adrian.core.domain.exceptions

import android.content.Context
import java.lang.Exception

sealed class UiException : kotlin.Exception() {
    class ContextException : Exception("Expected context not found")
    class ContextListenerException(context: Context, listenerName: String) :
        Exception("$context must implement $listenerName")
}