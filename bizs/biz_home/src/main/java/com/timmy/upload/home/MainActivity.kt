package com.timmy.upload.home

import android.content.Intent
import com.timmy.libbase.base.activity.BaseVbActivity
import com.timmy.upload.home.databinding.ActivityMainBinding

class MainActivity : BaseVbActivity<ActivityMainBinding>() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }

    override fun initData() {
    }

    override fun initListener() {
        mBinding.jumpToSecond.setOnClickListener {
            startActivity(Intent(this, SecondVbVmActivity::class.java))


        }
    }


}