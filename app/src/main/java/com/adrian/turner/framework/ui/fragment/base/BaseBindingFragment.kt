package com.adrian.turner.framework.ui.fragment.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.adrian.turner.utils.dialogs.BaseDialog

abstract class BaseBindingFragment<B: ViewBinding, VM: ViewModel>(
    layoutId: Int
): Fragment(layoutId) {

    protected abstract val viewModel: VM
    protected abstract val binding: B

    val dialog = BaseDialog()

}