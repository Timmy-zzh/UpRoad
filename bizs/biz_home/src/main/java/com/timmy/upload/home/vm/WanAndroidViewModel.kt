package com.timmy.upload.home.vm

import androidx.lifecycle.MutableLiveData
import com.timmy.libbase.base.vm.BaseViewModel
import com.timmy.libbase.net.RetrofitFactory
import com.timmy.upload.home.api.WanService
import com.timmy.upload.home.bean.AtricleList
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        val wanService = RetrofitFactory.instance.createService(WanService::class.java)
        val reposCall = wanService.getArticleListCall()

        reposCall.enqueue(object  :Callback<AtricleList>{
            override fun onResponse(call: Call<AtricleList>, response: Response<AtricleList>) {
                val atricleList = response.body()
            }

            override fun onFailure(call: Call<AtricleList>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

//        reposCall.enqueue(object : Callback<List<Repo>> {
//            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
//
//            }
//
//            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
//            }
//        })


    }
}