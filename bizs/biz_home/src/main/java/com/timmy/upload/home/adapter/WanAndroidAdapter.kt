package com.timmy.upload.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.timmy.upload.home.bean.Article
import com.timmy.upload.home.databinding.HomeItemWanAndroidBinding

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
    }

    fun setData(datas: List<Article>?) {
        this.datas = datas
        notifyDataSetChanged()
    }

    class WanViewHolder(binding: HomeItemWanAndroidBinding) : ViewHolder(binding.root) {
        val binding = binding
    }

}
