package com.timmy.workdemo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.transformer.MZScaleInTransformer

class MainActivity : AppCompatActivity() {
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
}