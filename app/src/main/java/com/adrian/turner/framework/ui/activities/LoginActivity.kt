package com.adrian.turner.framework.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import com.adrian.turner.R
import com.adrian.turner.adapter.viewmodel.LoginViewModel
import com.adrian.turner.adapter.viewmodel.factory.LoginViewModelFactory
import com.adrian.turner.databinding.ActivityLoginBinding
import com.adrian.turner.framework.ui.activities.base.BaseBindingActivity
import com.adrian.turner.utils.extensions.viewBinding

class LoginActivity : BaseBindingActivity<ActivityLoginBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by lazy {
        LoginViewModelFactory().create(LoginViewModel::class.java)
    }

    override val binding by viewBinding(ActivityLoginBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

}