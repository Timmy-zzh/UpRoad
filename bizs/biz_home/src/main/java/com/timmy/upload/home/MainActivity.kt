package com.timmy.upload.home

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import com.timmy.libbase.base.activity.BaseVbActivity
import com.timmy.upload.home.databinding.HomeActivityMainBinding
import com.timmy.upload.home.fragment.AigcFragment
import com.timmy.upload.home.fragment.WanAndroidFragment

/**
 * 首页：
 * - 布局：底部BottomNavigationView + Fragment 填充
 * - Fragment类型分为：玩安卓相关 + AI + 音视频 + 知识体系（Kotlin） + 我的
 * - 先完成玩安卓网络请求到页面展示内容
 */
class MainActivity : BaseVbActivity<HomeActivityMainBinding>() {

    private val fragments = ArrayList<Fragment>(5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initData() {
        // 添加Fragment到集合中
        fragments.add(WanAndroidFragment.instance())
        fragments.add(AigcFragment.instance())
        fragments.add(AigcFragment.instance())
        fragments.add(AigcFragment.instance())
        fragments.add(AigcFragment.instance())

        supportFragmentManager
            .beginTransaction()
            .add(R.id.flContent, fragments[0])
            .commit()
    }

    override fun initListener() {
        mBinding.bottomNavView.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.action_home -> switchFragment(fragments[0])
                    R.id.action_ai -> switchFragment(fragments[1])
                    R.id.action_category -> switchFragment(fragments[2])
                    R.id.action_find -> switchFragment(fragments[3])
                    R.id.action_me -> switchFragment(fragments[4])
                    else -> Log.e(TAG, "not menu")
                }
                return true
            }
        })
    }

    /**
     * 先隐藏，后展示
     */
    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.flContent, fragment)
            .commitAllowingStateLoss()
    }
}