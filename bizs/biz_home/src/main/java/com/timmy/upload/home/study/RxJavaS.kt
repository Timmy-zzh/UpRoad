package com.timmy.upload.home.study

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers

object RxJavaS {

    /**
     * eg1
     * RxJava学习使用
     * - 基本使用
     */
    fun basicUse() {
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

    /**
     * eg2 map操作符
     */
    fun mapOperate() {
        Observable.create(object : ObservableOnSubscribe<Int> {
            override fun subscribe(emitter: ObservableEmitter<Int>) { // 发射
                emitter.onNext(21)
                emitter.onNext(22)
                emitter.onNext(23)

                emitter.onComplete()
            }
        }).map { // int类型数据增加2后，转换成String类型数据
            val t = it + 2
            "result-$t"
        }.subscribe(object : Observer<String> {
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

    /**
     * 线程切换操作
     */
    fun threadChange() {
        Observable.create(object : ObservableOnSubscribe<Int> {
            override fun subscribe(emitter: ObservableEmitter<Int>) { // 发射
                emitter.onNext(31)
                emitter.onNext(32)
                emitter.onNext(33)
            }
        }).subscribeOn(Schedulers.io())    // 给上面切换到子线程
            .observeOn(AndroidSchedulers.mainThread())  // 给下面切换到主线程
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                    println("onSubscribe")
                }

                override fun onError(e: Throwable) {
                    println("onError:$e")
                }

                override fun onComplete() {
                    println("onComplete")
                }

                override fun onNext(t: Int) {
                    println("onNext:$t")
                }
            })
    }

    fun rxjavaS() {

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

}