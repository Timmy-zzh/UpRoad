package com.timmy.upload.home.api

import com.timmy.libbase.base.bean.BaseResult
import com.timmy.upload.home.bean.AtricleList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WanService {

    /**
     * 1.1 首页文章列表
     * https://www.wanandroid.com/article/list/0/json
     * 方法：GET
     * 参数：页码，拼接在连接中，从0开始。
     */
    @GET("article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page: Int = 0): BaseResult<AtricleList>


    @GET("article/list/{page}/json")
    fun getArticleListCall(@Path("page") page: Int = 0): Call<AtricleList>

    /**
     * 1.2 首页banner
     * https://www.wanandroid.com/banner/json
     * 方法：GET
    参数：无
     */
}