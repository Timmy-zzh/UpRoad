package com.timmy.upload.home.fragment

import androidx.fragment.app.Fragment
import com.timmy.libbase.base.activity.BaseVbVmActivity
import com.timmy.libbase.base.fragment.BaseVbVmFragment
import com.timmy.upload.home.databinding.HomeAigcBinding
import com.timmy.upload.home.vm.AigcViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.Call
import okhttp3.Callback
import okhttp3.EventListener
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit

class AigcFragment : BaseVbVmFragment<HomeAigcBinding, AigcViewModel>() {

    companion object {
        fun instance(): Fragment {
            return AigcFragment()
        }
    }

    override fun init() {

        //        httpGet()
        //        httpPost()
        //        studyRxJava()
        studyRxJava1()

    }

    /**
     * RxJava学习使用
     */
    private fun studyRxJava1() {
        // eg1
        Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(emitter: ObservableEmitter<String>) { // 发射
                emitter.onNext("11")
                emitter.onNext("12")
                emitter.onNext("13")

                emitter.onComplete()
            }
        }).subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                println("RxJava onSubscribe")
            }

            override fun onError(e: Throwable) {
                println("RxJava onError:$e")
            }

            override fun onComplete() {
                println("RxJava onComplete")
            }

            override fun onNext(t: String) {
                println("RxJava onNext:$t")
            }
        })
    }

    private fun studyRxJava() {

        // hook
        // 拦截实现者，全局监听，先交给自己设置的Observable进行处理
        RxJavaPlugins.setOnObservableAssembly {
            it
        } //        RxJavaPlugins.setInitIoSchedulerHandler {
        //            it
        //        }

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
        Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(emitter: ObservableEmitter<String>) { // 发射
                emitter.onNext("11")
                emitter.onNext("12")
                emitter.onNext("13")
            }
        }).doOnNext {
            println("doOnNext:$it")
        }.subscribe(object : Observer<String> {
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
        Observable.create(object : ObservableOnSubscribe<Int> {
            override fun subscribe(emitter: ObservableEmitter<Int>) { // 发射
                emitter.onNext(21)
                emitter.onNext(22)
                emitter.onNext(23)

                emitter.onComplete()
            }
        }).map { // int类型变成String类型数据
            val t = it + 2
            "res:$t"
        }.subscribe(object : Observer<String> {
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

        // eg3:线程切换
        Observable.just(1, 2, 3).flatMap { Observable.just("1") }
            .subscribeOn(Schedulers.io())    // 给上面切换到子线程
            .observeOn(AndroidSchedulers.mainThread())  // 给下面切换到主线程
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

    /**
     * ***********************OkHttpClient******************************
     */
    private fun httpPost() {

        val okHttpClient = OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS)
            .eventListener(object : EventListener() {
                override fun callStart(call: Call) {
                    super.callStart(call)
                }
            }).build()

        val json = JSONObject()
        json.put("key1", "value")
        json.put("age", 18)

        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body: RequestBody = json.toString().toRequestBody(mediaType)

        val request: Request = Request.Builder().url("https://www.baidu.com").post(body).build()
        val newCall: Call = okHttpClient.newCall(request)
        newCall.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
            }
        })


    }

    private fun httpGet() {

        val okHttpClient = OkHttpClient()

        val request: Request = Request.Builder().url("https://www.baidu.com").build()

        val newCall: Call = okHttpClient.newCall(request)

        val res: Response = newCall.execute()
        println("res:${res.isSuccessful} , ${res.body.toString()}")
        res.close()


        newCall.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
            }
        })

    }
}