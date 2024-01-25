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
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.plugins.RxJavaPlugins
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

        // 拦截实现者，全局监听，先交给自己设置的Observable进行处理
        RxJavaPlugins.setOnObservableAssembly {
            it
        }

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

        Observable.just(1, 2, 3)
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
            .flatMap { Observable.just("1") }
            .subscribeOn(Schedulers.newThread())    // 给上面切换到子线程
            .observeOn(AndroidSchedulers.mainThread())  // 给下面切换到主线程
            .subscribe {
                println(it)
            }

        // eg1
        Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(emitter: ObservableEmitter<String>) { // 发射
                emitter.onNext("11")
                emitter.onNext("12")
                emitter.onNext("13")
            }
        }).subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                println("onSubscribe")
            }

            override fun onError(e: Throwable) {
                println("onError:$e")
            }

            override fun onComplete() {
                println("onComplete")
            }

            override fun onNext(t: String) {
                println("onNext:$t")
            }
        })

        // eg2 map操作符
        Observable.create(object : ObservableOnSubscribe<String> {
                override fun subscribe(emitter: ObservableEmitter<String>) { // 发射
                    emitter.onNext("21")
                    emitter.onNext("22")
                    emitter.onNext("23")
                }
            })
            .map { // int类型变成String类型数据
                val t = it + 2
                "res:$t"
            }
            .subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                println("onSubscribe")
            }

            override fun onError(e: Throwable) {
                println("onError:$e")
            }

            override fun onComplete() {
                println("onComplete")
            }

            override fun onNext(t: String) {
                println("onNext:$t")
            }
        })
    }
}














