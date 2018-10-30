package com.conways.kotlinprojects.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.conways.kotlinprojects.R
import com.conways.kotlinprojects.R.id.item_fruit_icon
import com.conways.kotlinprojects.activity.DetialActivity

class FrutAdapter(context: Context?) : RecyclerView.Adapter<FrutAdapter.FruiHolder>() {


    var context: Context? = null

    init {
        this.context = context
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FruiHolder {
        return FruiHolder(LayoutInflater.from(context).inflate(R.layout.item_fruit, p0, false));
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(p0: FruiHolder, p1: Int) {
        p0.icom?.setOnClickListener{
            context?.startActivity(Intent(context,DetialActivity::class.java))
        }

    }


    class FruiHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icom:ImageView? = itemView.findViewById(R.id.item_fruit_icon)
    }

}

