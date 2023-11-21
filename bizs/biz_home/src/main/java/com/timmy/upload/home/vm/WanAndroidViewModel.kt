package com.timmy.upload.home.vm

import androidx.lifecycle.MutableLiveData
import com.timmy.libbase.base.vm.BaseViewModel
import com.timmy.libbase.net.RetrofitFactory
import com.timmy.upload.home.api.WanService
import com.timmy.upload.home.bean.AtricleList
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class WanAndroidViewModel : BaseViewModel() {

    val articleDataLV = MutableLiveData<AtricleList?>()

    init { // 获取网络数据
        loadData()
    }

    private fun loadData() {
        MainScope().launch {
            val wanService = RetrofitFactory.instance.createService(WanService::class.java)
            val articleList = wanService.getArticleList()
            println("articleList=$articleList")

            articleDataLV.postValue(articleList.data)
        }

    }
}