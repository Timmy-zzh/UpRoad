package com.timmy.libbase.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.timmy.libbase.base.vm.BaseViewModel
import com.timmy.libbase.ext.getVmClazz
import com.timmy.libbase.ext.inflateViewBindingWithGeneric

abstract class BaseVbVmFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment() {

    private var _viewModel: VM? = null
    val mViewModel: VM get() = _viewModel!!

    private var _binding: VB? = null
    val mBinding: VB get() = _binding!!
    abstract fun init()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = inflateViewBindingWithGeneric(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewModel = ViewModelProvider(this)[getVmClazz(this)]
        init()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _viewModel = null
    }

}