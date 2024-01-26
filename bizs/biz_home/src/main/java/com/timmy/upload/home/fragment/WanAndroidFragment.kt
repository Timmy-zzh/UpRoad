package com.timmy.upload.home.fragment

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.timmy.libbase.base.fragment.BaseVbVmFragment
import com.timmy.upload.home.adapter.WanAndroidAdapter
import com.timmy.upload.home.databinding.HomeFragmentWanAndroidBinding
import com.timmy.upload.home.vm.WanAndroidViewModel

/**
 * 首页
 * - 布局：列表，加头Banner
 * - 数据：网络请求 Retrofit
 * - 展示：RecyclerView Adapter
 */
class WanAndroidFragment : BaseVbVmFragment<HomeFragmentWanAndroidBinding, WanAndroidViewModel>() {

    private val mAdapter = WanAndroidAdapter()

    companion object {
        fun instance(): Fragment {
            return WanAndroidFragment()
        }
    }

    override fun init() {

        // RecyclerView控件初始化
        val recyclerView = mBinding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = mAdapter

        mViewModel.articleDataLV.observe(this) {
            mAdapter.setData(it?.datas)
        }
    }
}













