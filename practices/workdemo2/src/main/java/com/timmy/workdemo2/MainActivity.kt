package com.timmy.workdemo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.transformer.MZScaleInTransformer

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val banner = findViewById<Banner<Int, BannerImageAdapter<Int>>>(R.id.banner)

        banner.isAutoLoop(true)
        banner.setLoopTime((10 * 1000).toLong())
        banner.addPageTransformer(MZScaleInTransformer())

        banner.setAdapter(object : BannerImageAdapter<Int>(getImageList()) {
            override fun onBindView(holder: BannerImageHolder?, data: Int?, position: Int,
                size: Int) {
                holder?.imageView?.setImageResource(data!!)
            }
        })

        val mTvTest = findViewById<TextView>(R.id.mTvTest)
        val mBtnTest = findViewById<Button>(R.id.mBtnTest)

        mTvTest.setOnClickListener(this)

        mBtnTest.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {

            }
        })
    }

    private fun getImageList(): MutableList<Int> {
        val list = mutableListOf<Int>()
        list.add(R.mipmap.img_one)
        list.add(R.mipmap.img_two)
        list.add(R.mipmap.img_three)
        list.add(R.mipmap.img_four)
        list.add(R.mipmap.img_yuanxian)
        return list
    }

    override fun onClick(v: View?) {


    }
}