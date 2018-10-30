package com.conways.kotlinprojects.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.conways.kotlinprojects.R
import com.conways.kotlinprojects.activity.DetialActivity

class VpAdapter(context: Context?) : RecyclerView.Adapter<VpAdapter.VpHolder>() {

    var isRed:Boolean=false;

    var context: Context? = null

    init {
        this.context = context
    }

    open fun refresh(){
        if (isRed){
            isRed=false
        }else{
            isRed=true
        }

        this.notifyDataSetChanged()
    }



    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VpHolder {
        return VpHolder(LayoutInflater.from(context).inflate(R.layout.item_vp, p0, false))
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    override fun onBindViewHolder(p0: VpHolder, p1: Int) {

        if(p1%2==0){
            p0.itemView.setBackgroundColor(if(isRed) 0x4dff00ff else 0x4dff0000)
        }else{
            p0.itemView.setBackgroundColor(if(isRed) 0x4dffff00 else 0x4d0000ff)
        }

    }


    class VpHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icom:ImageView? = itemView.findViewById(R.id.item_vp_icon)
    }

}

