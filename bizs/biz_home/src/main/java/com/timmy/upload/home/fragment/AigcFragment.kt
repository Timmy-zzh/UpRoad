package com.timmy.upload.home.fragment

import androidx.fragment.app.Fragment
import com.timmy.libbase.base.fragment.BaseVbVmFragment
import com.timmy.upload.home.databinding.HomeAigcBinding
import com.timmy.upload.home.study.RxJavaS
import com.timmy.upload.home.vm.AigcViewModel

class AigcFragment : BaseVbVmFragment<HomeAigcBinding, AigcViewModel>() {

    companion object {
        fun instance(): Fragment {
            return AigcFragment()
        }
    }

    override fun init() {
//        RxJavaS.basicUse()
        RxJavaS.mapOperate()
    }

}