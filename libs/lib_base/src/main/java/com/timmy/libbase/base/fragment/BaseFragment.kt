package com.timmy.libbase.base.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.timmy.libbase.base.vm.BaseViewModel
import com.timmy.libbase.ext.getVmClazz

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : BaseVbFragment<VB>() {

    private var _viewModel: VM? = null
    val viewModel: VM get() = _viewModel!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewModel = ViewModelProvider(this)[getVmClazz(this)]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewModel = null
    }

}