package com.timmy.libbase.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.timmy.libbase.base.vm.BaseViewModel
import com.timmy.libbase.ext.getVmClazz
import com.timmy.libbase.ext.inflateViewBindingWithGeneric

abstract class BaseVbVmActivity<VB : ViewBinding, VM : BaseViewModel> : AppCompatActivity() {

    private var _viewModel: VM? = null
    val viewModel: VM get() = _viewModel!!

    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    abstract fun initListener()
    abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateVB()
        reflectVM()
        setContentView(binding.root)
        initListener()
        initData()
    }

    private fun inflateVB() {
        _binding = inflateViewBindingWithGeneric(layoutInflater)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        _viewModel = null
    }

    /**
     * 反射创建ViewModel
     */
    private fun reflectVM() {
//        _viewModel = ViewModelProvider(this).get(getVmClazz(this))
        _viewModel = ViewModelProvider(this)[getVmClazz(this)]
    }
}
