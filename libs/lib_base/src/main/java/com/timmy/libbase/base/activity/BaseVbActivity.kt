package com.timmy.libbase.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.timmy.libbase.ext.inflateViewBindingWithGeneric

/**
 * Activity的基类
 * 1、继承自系统 AppCompatActivity
 * 2、通过泛型设置xml文件，ViewBinding,泛型只有一个，没有设置ViewModel
 */
abstract class BaseVbActivity<VB : ViewBinding> : AppCompatActivity() {

    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    abstract fun initListener()
    abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateVB()
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
    }

}