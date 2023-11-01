package com.timmy.upload.home

import com.timmy.libbase.base.activity.BaseVbVmActivity
import com.timmy.upload.home.databinding.ActivitySecondBinding

class SecondVbVmActivity : BaseVbVmActivity<ActivitySecondBinding, SecondeViewModel>() {

    override fun initListener() {
        mBinding.tvContent.text = "ABC"

    }

    override fun initData() {
        mViewModel.initData("1222")
    }

}