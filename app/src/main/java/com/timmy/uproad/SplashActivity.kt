package com.timmy.uproad

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.timmy.libbase.base.activity.BaseVbActivity
import com.timmy.uproad.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 * 闪屏页：
 * - 处理黑白屏问题，设置windowBackground
 * - 倒计时
 * - 全屏
 * - 状态栏
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseVbActivity<ActivitySplashBinding>() {

    val mHandler = Handler(Looper.getMainLooper())
    var mTimer: Int = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initData() {
//        mHandler.postDelayed(countDown, 0)
//        countDown2()
        countDown3()
    }

    /**
     * 使用Flow方式实现倒计时
     */
    private fun countDown3() {
        lifecycleScope.launch {
            flow {
                (mTimer downTo 0).forEach {
                    delay(1000)
                    emit(it)
                }
            }.onStart {
                mBinding.tvCountDown.text =
                    String.format(getString(R.string.count_down_time), mTimer)
            }.onCompletion {
                jumpToNext()
            }.catch {
                Log.e(TAG, it.message ?: "Unknow Error")
            }.collect {
                mBinding.tvCountDown.text =
                    String.format(getString(R.string.count_down_time), it.toString())
            }
        }
    }

    /**
     * 倒计时实现方式二
     */
    private fun countDown2() {
        mBinding.tvCountDown.text = String.format(getString(R.string.count_down_time), 3)

        val countDownTimer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.d(TAG, "onTitck=$millisUntilFinished")
                mBinding.tvCountDown.text =
                    String.format(getString(R.string.count_down_time), millisUntilFinished / 1000)
            }

            override fun onFinish() {
                Log.d(TAG, "onFinish")
                jumpToNext()
            }
        }.start()
    }

    private val countDown = object : Runnable {
        override fun run() {
            if (mTimer <= 0) {
                jumpToNext()
            } else {
                mBinding.tvCountDown.text =
                    String.format(getString(R.string.count_down_time), mTimer) // "剩余(${mTimer}s)"
                mHandler.postDelayed(this, 1000)
            }
            mTimer--
        }
    }

    private fun jumpToNext() {
        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacks(countDown)
    }
}
