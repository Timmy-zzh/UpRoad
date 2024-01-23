package com.timmy.upload.home.fragment

import android.graphics.Bitmap
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.timmy.libbase.base.fragment.BaseVbVmFragment
import com.timmy.upload.home.adapter.WanAndroidAdapter
import com.timmy.upload.home.databinding.HomeFragmentWanAndroidBinding
import com.timmy.upload.home.vm.WanAndroidViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.function.Consumer
import java.util.function.Function

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

        studyRxJava()

    }

    /**
     * RxJava学习使用
     */
    private fun studyRxJava() {

        Observable.create<String> {     // create 创建被观察者
            it.onNext("1")
            it.onNext("2")
        }.subscribe(object : Observer<String> {   // subscribe 观察者订阅
            override fun onSubscribe(d: Disposable) {
                TODO("Not yet implemented")
            }

            override fun onError(e: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onComplete() {
                TODO("Not yet implemented")
            }

            override fun onNext(t: String) {
                TODO("Not yet implemented")
            }
        })

        Observable
            .just(1, 2, 3)
            .subscribeOn(Schedulers.newThread())  // 给上面切换到子线程
            .observeOn(AndroidSchedulers.mainThread()) // 给下面切换到主线程
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                    TODO("Not yet implemented")
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onComplete() {
                    TODO("Not yet implemented")
                }

                override fun onNext(t: Int) {
                    TODO("Not yet implemented")
                }
            })

        Observable.just(1, 2, 3)
            .map { // int类型变成String类型数据
            val t = it + 2
            "res:$t"
        }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
            println(it)
        }


        Observable.just(1, 2, 3)
            .flatMap { Observable.just("1") }
            .subscribeOn(Schedulers.newThread())    // 给上面切换到子线程
            .observeOn(AndroidSchedulers.mainThread())  // 给下面切换到主线程
            .subscribe {
                println(it)
            }

    }
}














