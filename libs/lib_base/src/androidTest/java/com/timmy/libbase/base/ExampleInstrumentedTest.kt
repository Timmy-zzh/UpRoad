package com.timmy.libbase.base

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() { // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.timmy.lib.base.test", appContext.packageName)

        Observable.just(1, 2, 3).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                    println("onSubscribe")
                }

                override fun onError(e: Throwable) {
                    println("onError:$e")
                }

                override fun onComplete() {
                    println("onSubscribe")
                }

                override fun onNext(t: Int) {
                    println("onNext:$t")
                }
            })
    }
}