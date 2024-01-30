package com.timmy.upload.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.timmy.upload.home.bean.Article
import com.timmy.upload.home.databinding.HomeItemWanAndroidBinding
import com.timmy.upload.home.study.GlideS

class WanAndroidAdapter : Adapter<WanAndroidAdapter.WanViewHolder>() {

    private var datas: List<Article>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WanViewHolder {
        val binding = HomeItemWanAndroidBinding.inflate(LayoutInflater.from(parent.context))
        return WanViewHolder(binding)

        //        return WanViewHolder(
        //            LayoutInflater.from(parent.context)
        //            .inflate(R.layout.home_item_wan_android, parent, false))
    }

    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }

    override fun onBindViewHolder(holder: WanViewHolder, position: Int) {
        holder.binding.tvTitle.text = "($position)" + datas!![position].title

        val imgUrl =
            "https://img1.baidu.com/it/u=830530130,2672008135&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500"

        val context: Context? = null
        Glide.with(context!!).load(imgUrl).into(holder.binding.ivImg)

    }

    fun setData(datas: List<Article>?) {
        this.datas = datas
        notifyDataSetChanged()
    }

    class WanViewHolder(binding: HomeItemWanAndroidBinding) : ViewHolder(binding.root) {
        val binding = binding
    }

}
