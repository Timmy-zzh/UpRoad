package com.timmy.upload.home.fragment

import androidx.fragment.app.Fragment
import com.timmy.libbase.base.fragment.BaseVbVmFragment
import com.timmy.upload.home.databinding.HomeActivityMainBinding
import com.timmy.upload.home.vm.WanAndroidViewModel

/**
 * 首页
 * - 布局：列表，加头Banner
 * - 数据：网络请求 Retrofit
 * - 展示：RecyclerView Adapter
 */
class WanAndroidFragment : BaseVbVmFragment<HomeActivityMainBinding, WanAndroidViewModel>() {

    companion object {
        fun instance(): Fragment {
            return WanAndroidFragment()
        }
    }

    override fun initListener() {

    }

    override fun initData() {
    }


}