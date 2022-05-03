package com.adrian.turner.adapter.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adrian.turner.adapter.viewmodel.LoginViewModel
import java.lang.IllegalArgumentException

class LoginViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            LoginViewModel() as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}