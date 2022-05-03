package com.adrian.turner.framework.ui.activities.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.adrian.turner.utils.dialogs.BaseDialog

abstract class BaseBindingActivity<B: ViewBinding, VM: ViewModel>: AppCompatActivity() {

    protected abstract val viewModel: VM
    protected abstract val binding: B

    val dialog = BaseDialog()

}