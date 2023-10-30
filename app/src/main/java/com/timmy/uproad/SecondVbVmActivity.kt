package com.timmy.uproad

import com.timmy.libbase.base.activity.BaseVbVmActivity
import com.timmy.uproad.databinding.ActivitySecondBinding

class SecondVbVmActivity : BaseVbVmActivity<ActivitySecondBinding, SecondeViewModel>() {

    override fun initListener() {
        binding.tvContent.text = "ABC"

    }

    override fun initData() {
        viewModel.initData("1222")
    }

}